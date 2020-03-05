package wang.bannong.gk5.rmdb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author wang.bannong(inc11003307@gmail.com)
 */
@Data
@Component
@ConfigurationProperties(prefix = "datasource")
public class DataSourceDB {

    public static String PLACEHOLDER_NAME_MAPPERS_PATH = "datasource.mappersPath";

    private DB     master;
    private DB     slave;

    @Data
    public static class DB {
        private String host;
        private int    port;
        private String db;
        private String username;
        private String password;
        private int    minIdle;
        private int    maxIdle;
        private long   connectionTimeout;
    }
}
