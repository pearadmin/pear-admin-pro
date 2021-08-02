package com.pearadmin.pro.modules.sys.domain;

import lombok.Data;

/**
 * 角色权限模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class SysTenantPower {

    /**
     * 编号
     * */
    private String id;

    /**
     * 租户编号
     * */
    private String tenantId;

    /**
     * 权限编号
     * */
    private String powerId;

}
