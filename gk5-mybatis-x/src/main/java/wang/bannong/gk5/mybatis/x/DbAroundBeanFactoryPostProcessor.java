package wang.bannong.gk5.mybatis.x;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.mybatis.x.config.DbProperties;

@Slf4j
@Component
public class DbAroundBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Getter
    private static String             mappersPath;
    @Getter
    private static String             primary;
    @Getter
    private static List<DbProperties> masters = new ArrayList<>();
    @Getter
    private static List<DbProperties> slaves  = new ArrayList<>();

    //    @Bean
    //    public MapperScannerConfigurer masterMapperScannerConfigurer() {
    //        return DbSupporter.mapperScannerConfigurer(DataSourcePrefix.master.name(), "wang.bannong.gk5.test.mapper");
    //    }
    //    @Bean
    //    public MapperScannerConfigurer slaveMapperScannerConfigurer() {
    //        return DbSupporter.mapperScannerConfigurer(DataSourcePrefix.slave.name(), "wang.bannong.gk5.test.mapper");
    //    }

    private static GlobalConfig globalConfig;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;

        YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
        yamlMapFactoryBean.setResources(new ClassPathResource("application.yml"));
        Map<String, Object> map = (Map<String, Object>) yamlMapFactoryBean.getObject().get("datasource");
        // mappersPath = (String) map.get("mappersPath");
        primary = (String) map.get("primary");
        List<Map<String, Object>> items = (List<Map<String, Object>>) map.get("dbs");
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        for (Map<String, Object> item : items) {
            DbProperties dbProperties = new DbProperties();
            String key = (String) item.get("key");
            if (key.startsWith(DataSourcePrefix.master.name())) {
                masters.add(dbProperties);
            } else if (key.startsWith(DataSourcePrefix.slave.name())) {
                slaves.add(dbProperties);
            } else {
                throw new RuntimeException("illegal datasource key:" + key);
            }
            dbProperties.setKey(key);
            dbProperties.setHost((String) item.get("host"));
            dbProperties.setPort((Integer) item.get("port"));
            dbProperties.setDb((String) item.get("db"));
            dbProperties.setUsername((String) item.get("username"));
            dbProperties.setPassword((String) item.get("password"));
            dbProperties.setMinIdle((Integer) item.get("minIdle"));
            dbProperties.setMaxIdle((Integer) item.get("maxIdle"));
            dbProperties.setConnectionTimeout((Integer) item.get("connectionTimeout"));
        }

        globalConfig = (GlobalConfig) beanFactory.getBean("globalConfig");
        log.info("init database connections......");

        if (CollectionUtils.isNotEmpty(masters)) {
            DbProperties dbProperties = null;
            // bn on 2020/2/8 默认采用一个主库，后期可以拓展增加多个主库，注意控制事务
            for (DbProperties dbPropertiesConf : masters) {
                if (primary.equals(dbPropertiesConf.getKey())) {
                    dbProperties = dbPropertiesConf;
                    break;
                }
            }
            if (dbProperties == null) {
                throw new RuntimeException("the primary database must be writable and begin with \"maste\"");
            }
            registerMaster(dbProperties, beanFactory);

            log.info("init read-database connections finished");
        }

        if (CollectionUtils.isNotEmpty(slaves)) {
            for (DbProperties dbProperties : slaves) {
                registerSlave(dbProperties, beanFactory);
            }
            log.info("init write-database connections finished");
        }
    }

    private void registerMaster(DbProperties dbProperties, DefaultListableBeanFactory beanFactory) {
        String key = dbProperties.getKey();
        DataSource dataSource = DbSupporter.buildPoolProperties(dbProperties);
        beanFactory.registerSingleton(key + "DataSource", dataSource);

        // 引用了MyBatis-Plus中的SqlSessionFactoryBean取代org.mybatis.spring.SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setGlobalConfig(globalConfig);
        sqlSessionFactory.setDataSource(dataSource);
        String sqlSessionFactoryBeanName = key + "SqlSessionFactory";
        beanFactory.registerSingleton(sqlSessionFactoryBeanName, sqlSessionFactory);

        SqlSessionTemplate sqlSessionTemplate = null;
        try {
            sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory.getObject());
        } catch (Exception e) {
            log.error("init Writable SqlSessionTemplate fail，db={}，exception detail:", dbProperties, e);
            throw new RuntimeException(e);
        }
        String SqlSessionTemplateBeanName = key + "SqlSessionTemplate";
        beanFactory.registerSingleton(SqlSessionTemplateBeanName, sqlSessionTemplate);

        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        beanFactory.registerSingleton(key + "TxManager", txManager);


        beanFactory.registerSingleton(key + "AnnotationDrivenTransactionManager",
                new TransactionManagementConfigurer() {
                    @Override
                    public PlatformTransactionManager annotationDrivenTransactionManager() {
                        return txManager;
                    }
                });

        TransactionTemplate transactionTemplate = new TransactionTemplate(txManager);
        beanFactory.registerSingleton(key + "TransactionTemplate", transactionTemplate);

    }

    private void registerSlave(DbProperties dbProperties, DefaultListableBeanFactory beanFactory) {
        String key = dbProperties.getKey();
        DataSource dataSource = DbSupporter.buildPoolProperties(dbProperties);
        beanFactory.registerSingleton(key + "DataSource", dataSource);

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setGlobalConfig(globalConfig);
        sqlSessionFactory.setDataSource(dataSource);
        String sqlSessionFactoryBeanName = key + "SqlSessionFactory";
        beanFactory.registerSingleton(sqlSessionFactoryBeanName, sqlSessionFactory);

        PaginationInterceptor pageInterceptor = // 开启 count 的 join 优化,只针对 left join !!!
                new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
        sqlSessionFactory.setPlugins(new Interceptor[]{pageInterceptor});
        SqlSessionTemplate sqlSessionTemplate = null;
        try {
            sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory.getObject());
        } catch (Exception e) {
            log.error("init Readable SqlSessionTemplate fail，db={}，exception detail:", dbProperties, e);
            throw new RuntimeException(e);
        }
        String SqlSessionTemplateBeanName = key + "SqlSessionTemplate";
        beanFactory.registerSingleton(SqlSessionTemplateBeanName, sqlSessionTemplate);
    }

}
