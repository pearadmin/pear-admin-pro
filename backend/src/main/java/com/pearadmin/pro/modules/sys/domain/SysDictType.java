package com.pearadmin.pro.modules.sys.domain;

import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;

/**
 * 数据字典模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
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
