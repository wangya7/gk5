package wang.bannong.gk5.sparrow.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import io.undertow.Undertow;
import wang.bannong.gk5.sparrow.common.spring.CrownHandlerExceptionResolver;
import wang.bannong.gk5.sparrow.common.spring.IEnumConverterFactory;
import wang.bannong.gk5.sparrow.common.spring.validator.ValidatorCollectionImpl;
import wang.bannong.gk5.sparrow.common.undertow.UndertowServerFactoryCustomizer;
import wang.bannong.gk5.sparrow.common.utils.JacksonUtils;
import wang.bannong.gk5.sparrow.framework.aspect.LogRecordAspect;

/**
 * Spring Boot 配置
 */
@Configuration
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    @Bean
    public LogRecordAspect logRecordAspect() {
        return new LogRecordAspect();
    }

    @Override
    public Validator getValidator() {
        return new SpringValidatorAdapter(new ValidatorCollectionImpl());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IEnumConverterFactory());
    }

    @Bean
    @ConditionalOnClass(Undertow.class)
    public UndertowServerFactoryCustomizer undertowServerFactoryCustomizer() {
        return new UndertowServerFactoryCustomizer();
    }

    @Bean
    @ConditionalOnMissingBean(RequestContextListener.class)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach(JacksonUtils.wrapperObjectMapper());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new CrownHandlerExceptionResolver());
    }

}
