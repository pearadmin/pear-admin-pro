package com.pearadmin.pro.common.aop;

import com.pearadmin.pro.common.aop.lang.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;

/**
 * Describe: 系统日志实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Aspect
public class LogAspect {

    /**
     * 切 面 编 程
     * */
    @Pointcut("@annotation(com.pearadmin.pro.common.aop.lang.annotation.Log) || @within(com.pearadmin.pro.common.aop.lang.annotation.Log)")
    public void dsPointCut() { }

    /**
     * 处 理 系 统 日 志
     * */
    @Around("dsPointCut()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object result;
        try {

            // 日 志 写 入
            System.out.println("执行日志切面");

            result = joinPoint.proceed();
        }catch (Exception exception){
            throw exception;
        }finally {
        }
        return result;
    }

    public Log getLogging(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        Log targetLog = targetClass.getAnnotation(Log.class);
        if ( targetLog != null) {
            return targetLog;
        } else {
            Method method = signature.getMethod();
            Log log = method.getAnnotation(Log.class);
            return log;
        }
    }
}