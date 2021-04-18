package com.pearadmin.pro.modules.sys.service;

import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysFile;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
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
}
