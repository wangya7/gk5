package wang.bannong.gk5.mybatis.x.db;

import lombok.Data;

@Data
public class DbProperties {
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
