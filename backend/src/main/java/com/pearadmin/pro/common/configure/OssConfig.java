package com.pearadmin.pro.common.configure;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Oss 阿里云配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Configuration
public class OssConfig {

    private static String endpoint = "123";

    private static String accessKeyId = "123";

    private static String accessKeySecret = "123";

    private volatile static OSS ossClient;

    private volatile static OSSClientBuilder ossClientBuilder;

    @Bean
    public OSS ossClient(){
        if (ossClient == null) {
            synchronized (OssConfig.class) {
                if (ossClient == null) {
                    ossClient = initOSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    public static OSSClientBuilder initOSSClientBuilder() {
        if (ossClientBuilder == null) {
            synchronized (OssConfig.class) {
                if (ossClientBuilder == null) {
                    ossClientBuilder = new OSSClientBuilder();
                }
            }
        }
        return ossClientBuilder;
    }
}
