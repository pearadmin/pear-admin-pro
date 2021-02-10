package com.pearadmin.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Describe: Spring Boot Application 启 动 类
 * Author: 就 眠 仪 式
 * CreateTime: 2020/10/23
 * */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EntranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntranceApplication.class, args);
    }
}
