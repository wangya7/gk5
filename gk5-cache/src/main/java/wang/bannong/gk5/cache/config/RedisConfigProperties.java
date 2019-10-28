package wang.bannong.gk5.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by bn. on 2018/8/4 下午5:17
 */
@Data
@Component
@ConfigurationProperties(prefix = "cache")
@PropertySource("classpath:application.yml")
public class RedisConfigProperties implements Serializable {
    private static final long serialVersionUID = -1515561047164933985L;

    private RedisCfg redis;

    @Data
    public static class RedisCfg implements Serializable {
        private static final long serialVersionUID = -8461021635945655273L;
        private String host;
        private int    port;
        private String password;
        private int    timeout;
        private int    poolMaxTotal;
        private int    poolMaxWaitMillis;
        private int    poolMaxIdle;
        private int    poolMinIdle;

        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
        private boolean poolTestOnBorrow;

    }
}
