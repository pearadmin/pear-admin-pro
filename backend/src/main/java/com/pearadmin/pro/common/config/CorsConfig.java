package com.pearadmin.pro.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Describe: 同源策略跨域配置
 * Author: 就 眠 仪 式
 * CreateTime: 2020/10/23
 * */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(168000);
    }

}