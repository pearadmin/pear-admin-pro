package com.pearadmin.pro.modules.not.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.not.domain.SysRemind;
import com.pearadmin.pro.modules.not.param.SysRemindRequest;
import java.util.List;

public interface SysRemindService extends IService<SysRemind> {

    /**
     * 获取提醒列表
     *
     * @param request 参数实体
     *
     * @return {@link SysRemind}
     * */
    List<SysRemind> list(SysRemindRequest request);

    /**
     * 获取提醒列表 (分页)
     *
     * @param request 参数实体
     *
     * @return {@link SysRemind}
     * */
    PageResponse<SysRemind> page(SysRemindRequest request);
}
