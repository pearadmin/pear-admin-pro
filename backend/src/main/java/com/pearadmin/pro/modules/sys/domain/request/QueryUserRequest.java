package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

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
