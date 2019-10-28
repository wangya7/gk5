package wang.bannong.gk5.test.ttm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import wang.bannong.gk5.test.ttm.config.TtmConfigProperties;
import wang.bannong.gk5.ttm.core.executor.impl.XxlJobSpringExecutor;

@Configuration
@EnableConfigurationProperties(TtmConfigProperties.class)
public class TtmBeanGenerator {

    @Autowired
    private TtmConfigProperties ttmConfigProperties;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(ttmConfigProperties.getAdminAddresses());
        xxlJobSpringExecutor.setAppName(ttmConfigProperties.getAppName());
        xxlJobSpringExecutor.setIp(ttmConfigProperties.getIp());
        xxlJobSpringExecutor.setPort(ttmConfigProperties.getPort());
        xxlJobSpringExecutor.setAccessToken(ttmConfigProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(ttmConfigProperties.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(ttmConfigProperties.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
