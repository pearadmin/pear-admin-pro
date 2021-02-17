package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

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
