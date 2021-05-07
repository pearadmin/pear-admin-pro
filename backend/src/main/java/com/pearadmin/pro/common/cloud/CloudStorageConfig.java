package com.pearadmin.pro.common.cloud;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 云存储配置
 * */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class CloudStorageConfig {

    private volatile static OSS ossClient;

    private volatile static OSSClientBuilder ossClientBuilder;

    private static String endpoint;

    private static String accessKeyId;

    private static String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.address}")
    private String address;

    @Value("${aliyun.oss.endpoint}")
    public void setEndpoint(String endpoint) {
        CloudStorageConfig.endpoint = endpoint;
    }

    @Value("${aliyun.oss.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        CloudStorageConfig.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.oss.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        CloudStorageConfig.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getAddress() {
        return address;
    }

    @Bean
    @Scope("prototype")
    public static OSS initOSSClient() {
        if (ossClient == null) {
            synchronized (CloudStorageConfig.class) {
                if (ossClient == null) {
                    ossClient = initOSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    public static OSSClientBuilder initOSSClientBuilder() {
        if (ossClientBuilder == null) {
            synchronized (CloudStorageConfig.class) {
                if (ossClientBuilder == null) {
                    ossClientBuilder = new OSSClientBuilder();
                }
            }
        }
        return ossClientBuilder;
    }
}
