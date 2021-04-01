package com.pearadmin.pro.common.interceptor.annotation;

import com.pearadmin.pro.common.interceptor.enums.Scope;

/**
 * 数据权限注解
 *
 * Author: 就眠仪式
 * CreateTime: 2021/04/01
 * */
public @interface DataScope {

    /**
     * 自 动 模 式
     *
     * Auto 模式下根据用户角色定义规则过滤数据
     *
     * 非 Auto 模式下, 采用强制数据权限过滤，即用户角色定义的规则失效
     * */
    Scope scope() default Scope.AUTO;
}
