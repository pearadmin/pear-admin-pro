package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

/**
 * Describe: 部门查询参数实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class QueryDeptRequest {

    /**
     * 部门名称
     * */
    private String name;
}
