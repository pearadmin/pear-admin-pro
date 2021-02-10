package com.pearadmin.pro.common.tools.support.proxy;

import com.pearadmin.pro.common.tools.support.proxy.aspect.Aspect;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AspectInterceptor implements InvocationHandler, Serializable {

    private final Object target;
    private final Aspect aspect;

    public AspectInterceptor(Object target, Aspect aspect){
        this.target = target;
        this.aspect = aspect;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        Object result = null;
        if(aspect.before(target,method,args)){
            try{
               result = method.invoke(target,args);
            }catch (InvocationTargetException e){
                if (aspect.exception(target, method, args, e.getTargetException())) {
                    throw e;
                }
            }
        }
        if (aspect.after(target, method, args, result)) {
            return result;
        }
        return null;
    }
}
