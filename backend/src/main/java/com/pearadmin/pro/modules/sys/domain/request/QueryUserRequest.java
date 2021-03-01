package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

/**
 * 用户查询参数实体
 * */
@Data
public class QueryUserRequest {

    /**
     * 用户名
     * */
    private String username;

    /**
     * 邮箱
     * */
    private String email;
}
