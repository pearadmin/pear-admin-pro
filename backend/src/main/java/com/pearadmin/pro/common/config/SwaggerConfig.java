package com.pearadmin.pro.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Describe: Swagger 文档配置
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 初 始 化 SWAGGER
     * */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pearadmin.pro"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 接 口 详 情
     * */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Boot Admin")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
