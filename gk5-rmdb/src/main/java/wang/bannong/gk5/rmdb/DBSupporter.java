package wang.bannong.gk5.rmdb;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import wang.bannong.gk5.rmdb.config.DataSourceDB;

/**
 * @author wang.bannong(inc11003307@gmail.com)
 * @create 2017-06-22 14:32
 */
public class DBSupporter {
    private static String url      = "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true&serverTimezone=UTC";
    private static String driver   = "com.mysql.cj.jdbc.Driver";
    private static String poolName = "Hikari-Alone";

    public static DataSource buildPoolProperties(DataSourceDB.DB dbProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format(url, dbProperties.getHost(), dbProperties.getPort(), dbProperties.getDb()));
        config.setDriverClassName(driver);
        config.setUsername(dbProperties.getUsername());
        config.setPassword(dbProperties.getPassword());
        // 自动提交从池中返回的连接 默认true
        config.setAutoCommit(true);
        // 连接允许在池中闲置的最长时间 默认600000
        config.setIdleTimeout(600000);
        // 池中维护的最小空闲连接数 默认10
        config.setMinimumIdle(dbProperties.getMinIdle());
        // 池中最大连接数，包括闲置和使用中的连接 默认10
        config.setMaximumPoolSize(dbProperties.getMaxIdle());
        // 等待来自池的连接的最大毫秒数 默认30000
        config.setConnectionTimeout(dbProperties.getConnectionTimeout());
        config.setPoolName(poolName);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }
}
