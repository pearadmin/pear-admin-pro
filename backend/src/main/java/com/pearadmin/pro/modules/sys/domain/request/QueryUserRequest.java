package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Describe: 用户查询参数实体
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/24
 * */
@Data
@Alias("QueryUserRequest")
public class QueryUserRequest {

    /**
     * 账户
     * */
    private String username;

    /**
     * 邮箱
     * */
    private String email;
}
