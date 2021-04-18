package com.pearadmin.pro.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.job.domain.SysJob;
import com.pearadmin.pro.modules.job.param.SysJobRequest;
import java.util.List;

public interface SysJobService extends IService<SysJob> {

    List<SysJob> list(SysJobRequest request);

    PageResponse<SysJob> page(SysJobRequest request);
}
