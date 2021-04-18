package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysPost;
import com.pearadmin.pro.modules.sys.param.SysPostRequest;
import java.util.List;

public interface SysPostService extends IService<SysPost> {

    List<SysPost> list(SysPostRequest request);

    PageResponse<SysPost> page(SysPostRequest request);
}
