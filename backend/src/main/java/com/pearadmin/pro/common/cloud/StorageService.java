package com.pearadmin.pro.common.cloud;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    /**
     * 上传文件
     *
     * @param file 文件
     *
     * @return String 存储路径
     * */
    String upload(MultipartFile file);

}
