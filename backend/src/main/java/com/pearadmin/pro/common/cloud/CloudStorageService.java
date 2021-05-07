package com.pearadmin.pro.common.cloud;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class CloudStorageService {

    @Resource
    private CloudStorageConfig cloudStorageConfig;

    @Resource
    private OSS ossClient;

    public String upload(MultipartFile file)
    {
        try {
            String fileName = UUID.randomUUID().toString();
            String filePath = cloudStorageConfig.getAddress() + fileName;
            InputStream inputStream = file.getInputStream();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentDisposition("inline;filename=" + fileName);

            ossClient.putObject(cloudStorageConfig.getBucketName(),fileName,inputStream,objectMetadata);

            return filePath;

        } catch (IOException e) {

            return null;
        }
    }

    public void remove(String path)
    {
        ossClient.deleteObject(cloudStorageConfig.getBucketName(),path);
    }

}
