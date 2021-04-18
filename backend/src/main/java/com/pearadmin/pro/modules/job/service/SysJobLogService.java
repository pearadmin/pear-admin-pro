package com.pearadmin.pro.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.job.domain.SysJobLog;
import com.pearadmin.pro.modules.job.param.SysJobLogRequest;
import java.util.List;

public interface SysJobLogService extends IService<SysJobLog> {

    /**
     * 获取任务日志列表
     *
     * @param request 查询参数
     * */
    List<SysJobLog> list(SysJobLogRequest request);

    /**
     * 获取任务日志列表 (分页)
     *
     * @param request 查询参数
     * */
    PageResponse<SysJobLog> page(SysJobLogRequest request);
}
