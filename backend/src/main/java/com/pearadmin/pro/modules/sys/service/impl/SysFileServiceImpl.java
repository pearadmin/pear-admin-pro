package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.cloud.StorageConfig;
import com.pearadmin.pro.common.cloud.impl.CloudStorageConfig;
import com.pearadmin.pro.common.cloud.impl.CloudStorageService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.SysFile;
import com.pearadmin.pro.modules.sys.repository.SysFileRepository;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
import com.pearadmin.pro.modules.sys.service.SysFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileRepository, SysFile> implements SysFileService {

    @Resource
    private StorageConfig storageConfig;

    @Resource
    private CloudStorageConfig cloudStorageConfig;

    @Resource
    private CloudStorageService cloudStorageService;

    @Resource
    private SysFileRepository sysFileRepository;

    @Override
    public List<SysFile> list(SysFileRequest request) {
        return sysFileRepository.selectFile(request);
    }

    @Override
    public PageResponse<SysFile> page(SysFileRequest request) {
        return Pageable.of(request,(()-> sysFileRepository.selectFile(request)));
    }

    @Override
    @Transactional
    public List<String> upload(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        files.forEach(file -> {
            urls.add(upload(file));
        });
        return urls;
    }

    @Override
    @Transactional
    public String upload(MultipartFile file) {

        /// 基础信息
        SysFile sysFile = parseInfo(file);

        /// 本地存储
        if(storageConfig.getLocation().equals("aliyun")) {

            /// 阿里存储
            String filePath = cloudStorageService.upload(file);

            /// 附加信息
            sysFile.setPath(filePath);
            sysFile.setBucket(cloudStorageConfig.getBucketName());

        }

        /// 存 储 记 录
        sysFileRepository.insert(sysFile);

        return null;
    }

    /**
     * 解析文件信息
     *
     * @param file 文件实体
     *
     * @return {@link SysFile} 文件信息
     * */
    private SysFile parseInfo(MultipartFile file){

        String fileName = file.getOriginalFilename();

        SysFile sysFile = new SysFile();
        sysFile.setLocation(storageConfig.getLocation());
        sysFile.setName(file.getOriginalFilename());
        sysFile.setSuffix(fileName.substring(fileName.lastIndexOf(".")));
        sysFile.setSize(file.getSize() / 1024);

        return sysFile;
    }
}
