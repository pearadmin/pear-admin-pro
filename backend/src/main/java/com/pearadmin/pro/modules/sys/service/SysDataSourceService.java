package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysDataSource;
import com.pearadmin.pro.modules.sys.param.SysDataSourceRequest;

import java.util.List;

public interface SysDataSourceService extends IService<SysDataSource> {

    List<SysDataSource> list(SysDataSourceRequest request);

    PageResponse<SysDataSource> page(SysDataSourceRequest request);
}
