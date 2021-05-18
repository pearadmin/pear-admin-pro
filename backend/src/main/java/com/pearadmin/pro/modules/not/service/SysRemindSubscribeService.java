package com.pearadmin.pro.modules.not.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.tools.support.server.server.Sys;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.not.domain.SysRemindSubscribe;
import com.pearadmin.pro.modules.not.param.SysRemindSubscribeRequest;

import java.util.List;

public interface SysRemindSubscribeService extends IService<SysRemindSubscribe> {

    /**
     * 获取订阅列表
     *
     * @param request 参数实体
     *
     * @return {@link SysRemindSubscribe}
     * */
    List<SysRemindSubscribe> list(SysRemindSubscribeRequest request);

    /**
     * 获取订阅列表 (分页)
     *
     * @param request 参数实体
     *
     * @return {@link SysRemindSubscribe}
     * */
    PageResponse<SysRemindSubscribe> page(SysRemindSubscribeRequest request);
}
