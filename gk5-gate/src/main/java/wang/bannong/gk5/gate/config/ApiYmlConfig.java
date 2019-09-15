package wang.bannong.gk5.gate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang.bannong on 2018/5/13 下午10:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "gate")
@PropertySource(value = "classpath:gate-api.yml")
public class ApiYmlConfig {

    private Map<String, Map<String, String>> apiMap = new HashMap<>();

}
