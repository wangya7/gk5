package wang.bannong.gk5.test.ttm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "ttm")
@PropertySource("classpath:application.yml")
public class TtmConfigProperties {

    private String adminAddresses;
    private String appName;
    private String ip;
    private int    port;
    private String accessToken;
    private String logPath;
    private int    logRetentionDays;
}
