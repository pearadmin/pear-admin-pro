package com.pearadmin.pro.common.cloud.impl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 腾讯云 Oss 存储配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/21
 * */
@Data
@Component
@ConfigurationProperties(prefix = "tencent.oss")
public class TencentCloudStorageConfig {
}
