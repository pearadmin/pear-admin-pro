package com.pearadmin.pro.modules.sys.domain;

import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;

@Data
public class SysDictData extends BaseDomain {

    /**
     * 编号
     * */
    private String id;

    /**
     * 标签
     * */
    private String label;

    /**
     * 值
     * */
    private String value;

    /**
     * 启用
     * */
    private boolean enable;

    /**
     * 标识
     * */
    private String code;

}
