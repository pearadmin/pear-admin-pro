package com.pearadmin.pro.common.tools.core;

import com.pearadmin.pro.common.tools.support.proxy.AspectInterceptor;
import com.pearadmin.pro.common.tools.support.proxy.aspect.Aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Proxy 代理 Utils 函数
 *
 * @author 就眠仪式
 * */
public class ProxyUtil {

    /**
     * 创建切面代理对象
     * */
    public static<T> T proxy(T target, Aspect aspect){
        return proxy(target.getClass().getClassLoader(), new AspectInterceptor(target, aspect), target.getClass().getInterfaces());
    }

    /**
     * 创建动态代理对象
     * */
    public static <T> T proxy(ClassLoader classloader, InvocationHandler invocationHandler, Class<?>... interfaces) {
        return (T) Proxy.newProxyInstance(classloader, interfaces, invocationHandler);
    }
}
