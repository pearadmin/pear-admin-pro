package com.pearadmin.pro.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 * Swagger 文档配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Configuration
@EnableSwagger2
public class SwaggerConfig  {

    @Bean
    public Docket docker(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pearadmin"))
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Pear Admin Pro",
                "基于 Spring Boot 的企业级开发平台" ,
                "",
                "www.pearadmin.com",new Contact("","",""),
                "",
                "",
                new ArrayList<>()
        );
    }
}
