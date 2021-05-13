package com.pearadmin.pro.common.cloud.impl;

import com.pearadmin.pro.common.cloud.StorageService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 七牛云 Oss 存储配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/21
 * */
@Component
public class QiniuCloudStorageService implements StorageService {

    @Override
    public String upload(MultipartFile file) {
        return null;
    }
}
