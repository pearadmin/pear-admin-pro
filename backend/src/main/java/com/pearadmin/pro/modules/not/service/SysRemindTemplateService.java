package com.pearadmin.pro.modules.not.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.not.domain.SysRemindTemplate;
import com.pearadmin.pro.modules.not.param.SysRemindTemplateRequest;

import java.util.List;

public interface SysRemindTemplateService extends IService<SysRemindTemplate> {

    /**
     * 获取提醒模板列表
     *
     * @param request 参数实体
     *
     * @return {@link SysRemindTemplate}
     * */
    List<SysRemindTemplate> list(SysRemindTemplateRequest request);

    /**
     * 获取提醒模板列表 (分页)
     *
     * @param request 参数实体
     *
     * @return {@link SysRemindTemplate}
     * */
    PageResponse<SysRemindTemplate> page(SysRemindTemplateRequest request);

}
