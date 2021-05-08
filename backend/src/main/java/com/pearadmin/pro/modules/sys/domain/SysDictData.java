package com.pearadmin.pro.modules.sys.domain;

import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 数据字典模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@EqualsAndHashCode(callSuper = true)
@Data
@Alias("SysDictData")
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

    /**
     * 排序
     * */
    private String sort;

}
