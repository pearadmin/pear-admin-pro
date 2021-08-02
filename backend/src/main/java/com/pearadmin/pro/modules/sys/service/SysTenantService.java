package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysTenant;
import com.pearadmin.pro.modules.sys.param.SysTenantRequest;
import java.util.List;

public interface SysTenantService extends IService<SysTenant> {

    /**
     * 获取租户列表
     *
     * @param request 参数实体
     *
     * @return {@link SysTenant}
     * */
    List<SysTenant> list(SysTenantRequest request);

    /**
     * 获取租户列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysTenant}
     * */
    PageResponse<SysTenant> page(SysTenantRequest request);
}
