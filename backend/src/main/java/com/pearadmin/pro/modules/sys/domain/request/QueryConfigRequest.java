package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

/**
 * Describe: 配置查询参数实体
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
@Data
public class QueryConfigRequest {

    /**
     * 配置名称
     * */
     private String name;

    /**
     * 配置标识
     * */
    private String key;
}
