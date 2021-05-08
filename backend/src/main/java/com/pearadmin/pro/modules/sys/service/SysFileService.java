package com.pearadmin.pro.modules.sys.service;

import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysFile;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SysFileService {

    /**
     * 获取文件列表
     *
     * @param request 查询参数
     * */
    List<SysFile> list(SysFileRequest request);

    /**
     * 获取文件列表
     *
     * @param request 查询参数
     * */
    PageResponse<SysFile> page(SysFileRequest request);

    /**
     * 文件上传
     *
     * @param file 文件本身
     * */
    String upload(MultipartFile file);

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * */
    List<String> upload(List<MultipartFile> files);

}
