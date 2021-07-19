package com.golaxy.machine.config;

import com.golaxy.machine.common.contants.ResultContants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger3配置Bean
 * @author lizhongpeng 2020/09/22 22:20
 */
@Configuration
@ConditionalOnProperty(prefix = "swagger-config", value = {"enable"}, havingValue = "true")
public class SwaggerConfig implements WebMvcConfigurer {
    @Value("${swagger-config.title}")
    protected String title;
    @Value("${swagger-config.version}")
    protected String version;
    @Value("${swagger-config.author}")
    protected String author;
    @Value("${swagger-config.url}")
    protected String url;
    @Value("${swagger-config.email}")
    protected String email;
    @Value("${swagger-config.description}")
    protected String description;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(new ApiKey(ResultContants.TOKEN, ResultContants.TOKEN, "header"));
        return securitySchemes;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .contact(new Contact(author, url, email))
                .description(description)
                .build();
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference(ResultContants.TOKEN, new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }

}

