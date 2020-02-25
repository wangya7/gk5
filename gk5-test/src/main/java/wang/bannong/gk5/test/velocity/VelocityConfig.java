package wang.bannong.gk5.test.velocity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import wang.bannong.gk5.velocity.sb.autoconfigure.VelocityProperties;
import wang.bannong.gk5.velocity.sb.web.servlet.view.EmbeddedVelocityViewResolver;

@Configuration
public class VelocityConfig {
    @Bean
    public EmbeddedVelocityViewResolver velocityViewResolver(VelocityProperties properties) {
        EmbeddedVelocityViewResolver resolver = new EmbeddedVelocityViewResolver();
        properties.applyToMvcViewResolver(resolver);
        resolver.setRedirectHttp10Compatible(false);
        return resolver;
    }
}
