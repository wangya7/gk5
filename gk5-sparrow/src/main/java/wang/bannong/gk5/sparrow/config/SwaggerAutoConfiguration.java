package wang.bannong.gk5.sparrow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import wang.bannong.gk5.sparrow.common.utils.JWTUtils;

/**
 * RESTful 服务 API 管理框架 Swagger 配置初始化
 */
@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("https://bn.zdautoservice.com")
                .globalOperationParameters(getParameters())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build().useDefaultResponseMessages(false);
    }

    /**
     * 获取swagger ApiInfo
     *
     * @return
     */
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sparrow API")
                .description("Sparrow Swagger API 文档")
                .termsOfServiceUrl("https://github.com/wangya7/gk5")
                .version("1.0")
                .contact(new Contact("bn", "https://github.com/wangya7", "bannongvipp@163.com"))
                .build();
    }

    /**
     * 获取Swagger参数
     *
     * @return
     */
    List<Parameter> getParameters() {
        return Collections.singletonList(new ParameterBuilder()
                .name("Authorization")
                .defaultValue("Bearer " + JWTUtils.generate(1))
                .description("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());
    }
}
