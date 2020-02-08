package wang.bannong.gk5.mybatis.x;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.mybatis.x.config.DataSourceDB;

@Slf4j
@Configuration
@EnableConfigurationProperties(DataSourceDB.class)
public class DBAroundBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Autowired
    private DataSourceDB dataSourceDB;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("初始化持久层连接信息......");
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;


        Map<Boolean, List<DataSourceDB.DB>> partition
                = dataSourceDB.getDbs()
                              .stream()
                              .collect(Collectors.partitioningBy(db -> DBSupporter.isMasterDB(db)));

        List<DataSourceDB.DB> masters = partition.get(Boolean.TRUE);
        if (CollectionUtils.isNotEmpty(masters)) {
            String primary = dataSourceDB.getPrimary();
            DataSourceDB.DB db = null;
            // bn on 2020/2/8 默认采用一个主库，后期可以拓展增加多个主库，注意控制事务
            for (DataSourceDB.DB dbConf : masters) {
                if (primary.equals(dbConf.getKey())) {
                    db = dbConf;
                    break;
                }
            }
            if (db == null) {
                log.error("默认的数据库必须是主库，以\"master\"开头，请重新配置默认数据库");
                throw new RuntimeException("重新配置默认数据库");
            }
            registerMaster(db, beanFactory);

            log.info("初始化持久层连接信息...写库加载完成");
        }

        List<DataSourceDB.DB> slaves = partition.get(Boolean.FALSE);
        if (CollectionUtils.isNotEmpty(slaves)) {
            for (DataSourceDB.DB db : slaves) {
                registerSlave(db, beanFactory);
            }
            log.info("初始化持久层连接信息...读库加载完成");
        }
    }

    private void registerMaster(DataSourceDB.DB db, ConfigurableListableBeanFactory beanFactory) {
        String key = db.getKey();
        DataSource dataSource = DBSupporter.buildPoolProperties(db);
        beanFactory.registerSingleton(key + "DataSource", dataSource);

        // 引用了MyBatis-Plus中的SqlSessionFactoryBean取代org.mybatis.spring.SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        beanFactory.registerSingleton(key + "SqlSessionFactory", sqlSessionFactory);

        SqlSessionTemplate sqlSessionTemplate = null;
        try {
            sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory.getObject());
        } catch (Exception e) {
            log.error("初始化写库SqlSessionTemplate异常，对应的db信息【{}】，异常信息:", db, e);
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

        String mapperPath = dataSourceDB.getMappersPath();
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        log.info("Mybatis加载Mapper路径:{}", mapperPath);
        mapperScannerConfigurer.setBasePackage(mapperPath);
        mapperScannerConfigurer.setSqlSessionTemplateBeanName(SqlSessionTemplateBeanName);
        mapperScannerConfigurer.setNameGenerator(new MapperBeanNameGenerator(key));
        beanFactory.registerSingleton(key + "MapperScannerConfigurer", mapperScannerConfigurer);
    }

    private void registerSlave(DataSourceDB.DB db, ConfigurableListableBeanFactory beanFactory) {
        String key = db.getKey();
        DataSource dataSource = DBSupporter.buildPoolProperties(db);
        beanFactory.registerSingleton(key + "DataSource", dataSource);

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        beanFactory.registerSingleton(key + "SqlSessionFactory", sqlSessionFactory);

        PaginationInterceptor pageInterceptor = // 开启 count 的 join 优化,只针对 left join !!!
                new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
        sqlSessionFactory.setPlugins(new Interceptor[]{pageInterceptor});
        SqlSessionTemplate sqlSessionTemplate = null;
        try {
            sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory.getObject());
        } catch (Exception e) {
            log.error("初始化读库SqlSessionTemplate异常，对应的db信息【{}】，异常信息:", db, e);
            throw new RuntimeException(e);
        }
        String SqlSessionTemplateBeanName = key + "SqlSessionTemplate";
        beanFactory.registerSingleton(SqlSessionTemplateBeanName, sqlSessionTemplate);

        String mapperPath = dataSourceDB.getMappersPath();
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage(mapperPath);
        mapperScannerConfigurer.setSqlSessionTemplateBeanName(SqlSessionTemplateBeanName);
        mapperScannerConfigurer.setNameGenerator(new MapperBeanNameGenerator(key));
        beanFactory.registerSingleton(key + "MapperScannerConfigurer", mapperScannerConfigurer);
    }
}
