package wang.bannong.gk5.mybatis.x;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class DBSupporter {

    private static String url                        = "jdbc:p6spy:mysql://%s:%d/%s?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private static String driver                     = "com.p6spy.engine.spy.P6SpyDriver";
    private static String poolName                   = "Hikari-MyBatisX";
    private static String SqlSessionTemplateBeanName = "%sSqlSessionTemplate";


    public static DataSource buildPoolProperties(DBProperties db) {
        HikariConfig config = new HikariConfig();
        String dataSourceUrl = String.format(url, db.getHost(), db.getPort(), db.getDb());
        config.setJdbcUrl(dataSourceUrl);
        config.setDriverClassName(driver);
        config.setUsername(db.getUsername());
        config.setPassword(db.getPassword());
        config.setAutoCommit(true);
        config.setIdleTimeout(600000);
        config.setMinimumIdle(db.getMinIdle());
        config.setMaximumPoolSize(db.getMaxIdle());
        config.setConnectionTimeout(db.getConnectionTimeout());
        config.setPoolName(poolName);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    /**
     * MyBatis-Spring提供了一个MapperFactoryBean，可以将数据映射接口转为Spring Bean
     * 开发中有很多的接口需要转换为Bean，一个个配置就显得恶心啦，所有出现了这个MapperScannerConfigurer
     * <p>
     * MapperScannerConfigurer将扫描basePackage所指定的包下的所有接口类（包括子类），如果它们在SQL映射
     * 文件中定义过，则将它们动态定义为一个Spring Bean，这样，我们在Service中就可以直接注入映射接口的bean
     */
    public static MapperScannerConfigurer mapperScannerConfigurer(String key, String mappersPath) throws Exception {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        log.info("DataSource-{} loading MappersPath:{}", key, mappersPath);
        configurer.setBasePackage(mappersPath);
        configurer.setSqlSessionTemplateBeanName(String.format(SqlSessionTemplateBeanName, key));
        configurer.setNameGenerator(new MapperBeanNameGenerator(key));
        return configurer;
    }
}
