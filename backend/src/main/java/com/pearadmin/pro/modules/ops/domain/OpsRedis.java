package com.pearadmin.pro.modules.ops.domain;

import lombok.Data;

@Data
public class OpsRedis {

    /**
     * 键
     * */
    private String key;

    /**
     * 值
     * */
    private String value;

}
