package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

/**
 * Describe: 用户查询参数实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
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
