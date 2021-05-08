package com.pearadmin.pro.common.cloud.impl;

import lombok.Data;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 阿里云 Oss 存储配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/21
 * */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class CloudStorageConfig {

    private volatile static OSS ossClient;

    private volatile static OSSClientBuilder ossClientBuilder;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    public String getBucketName() {
        return bucketName;
    }

    public String getEndpoint() { return endpoint; }

    @Bean
    public OSS initOSSClient() {

        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
