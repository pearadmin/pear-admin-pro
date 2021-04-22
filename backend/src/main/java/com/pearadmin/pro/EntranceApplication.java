package com.pearadmin.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Spring Boot Application 启 动 类
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EntranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntranceApplication.class, args);
    }
}
