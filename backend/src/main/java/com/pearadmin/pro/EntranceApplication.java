package com.pearadmin.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EntranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntranceApplication.class, args);
    }
}
