package com.pearadmin.pro.common.interceptor.annotation;

import com.pearadmin.pro.common.interceptor.enums.Scope;

/**
 * Describe: 数据权限注解
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public @interface DataScope {

    /**
     * 自 动 模 式
     * */
    Scope scope() default Scope.AUTO;

}
