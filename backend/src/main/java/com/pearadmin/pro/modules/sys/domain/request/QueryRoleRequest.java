package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

/**
 * Describe: 角色查询参数实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class QueryRoleRequest {

    /**
     * 角色名称
     * */
    private String name;

    /**
     * 角色权鉴
     * */
    private String code;

}
