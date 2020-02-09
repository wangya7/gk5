package wang.bannong.gk5.mybatis.x;

import lombok.Data;

@Data
public class DBProperties {
    // 用于区分DataSource
    private String key;
    private String host;
    private int    port;
    private String db;
    private String username;
    private String password;
    private int    minIdle;
    private int    maxIdle;
    private int    connectionTimeout;
}
