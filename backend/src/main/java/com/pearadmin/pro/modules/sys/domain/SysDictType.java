package com.pearadmin.pro.modules.sys.domain;

import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;

@Data
public class SysDictType extends BaseDomain {

    /**
     * 编号
     * */
    private String id;

    /**
     * 名称
     * */
    private String name;

    /**
     * 标识
     * */
    private String code;

    /**
     * 启用
     * */
    private boolean enable;

}
