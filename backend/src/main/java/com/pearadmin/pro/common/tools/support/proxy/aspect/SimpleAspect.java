package com.pearadmin.pro.common.tools.support.proxy.aspect;

import java.lang.reflect.Method;

public class SimpleAspect implements Aspect {

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args, Object result) {
        return true;
    }

    @Override
    public boolean exception(Object target, Method method, Object[] args, Throwable e) {
        return true;
    }
}
