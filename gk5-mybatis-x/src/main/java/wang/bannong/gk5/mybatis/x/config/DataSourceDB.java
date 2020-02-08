package wang.bannong.gk5.mybatis.x.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "datasource")
public class DataSourceDB {
    private String   mappersPath;
    private String   primary;
    private List<DB> dbs;

    @Data
    public static class DB {
        // 用于区分DataSource
        private String key;
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