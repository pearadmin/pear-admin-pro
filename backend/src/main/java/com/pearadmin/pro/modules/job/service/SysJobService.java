package com.pearadmin.pro.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.job.domain.SysJob;
import com.pearadmin.pro.modules.job.param.SysJobRequest;
import java.util.List;

/**
 * @author 就眠仪式
 */
public interface SysJobService extends IService<SysJob> {

    /**
     * 获取任务列表
     *
     * @param request 查询参数
     * */
    List<SysJob> list(SysJobRequest request);

    /**
     * 获取任务列表 (分页)
     *
     * @param request 查询参数
     * */
    PageResponse<SysJob> page(SysJobRequest request);
}
