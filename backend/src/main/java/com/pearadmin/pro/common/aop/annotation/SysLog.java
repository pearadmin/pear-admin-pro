package com.pearadmin.pro.common.aop.annotation;

import com.pearadmin.pro.common.aop.enums.Action;

import java.lang.annotation.*;

/**
 * 行 为 日 志 注 解
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/10/23
 * */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SysLog {

    /**
     * Title 默认输入
     * */
    String title() default "暂无标题";

    /**
     * Describe 默认输入
     * */
    String describe() default "暂无描述";

    /**
     * Action 操作类型
     * */
    Action action() default Action.QUERY;

}
