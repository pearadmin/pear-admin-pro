package com.pearadmin.pro.common.tools.support.proxy.aspect;

import java.lang.reflect.Method;

public interface Aspect {

    /**
     * 前置 Process
     * */
    boolean before(Object target, Method method, Object[] args);

    /**
     * 后置 Process
     * */
    boolean after(Object target, Method method, Object[] args, Object result);

    /**
     * 异常 Process
     * */
    boolean exception(Object target, Method method, Object[] args, Throwable e);

}
