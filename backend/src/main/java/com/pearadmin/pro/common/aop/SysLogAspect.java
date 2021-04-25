package com.pearadmin.pro.common.aop;

import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.common.aop.enums.Action;
import com.pearadmin.pro.common.context.BaseContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 系统日志实现
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Aspect
@Component
public class SysLogAspect {

    /**
     * 基 础 上 下 文
     * */
    @Resource
    private BaseContext context;

    /**
     * 切 面 编 程
     * */
    @Pointcut("@annotation(com.pearadmin.pro.common.aop.annotation.SysLog) || @within(com.pearadmin.pro.common.aop.annotation.SysLog)")
    public void dsPointCut() { }

    /**
     * 处 理 系 统 日 志
     * */
    @Around("dsPointCut()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        Object result = null;

        // 注解解析
        SysLog annotation = getAnnotation(joinPoint);
        String title = annotation.title();
        Action action = annotation.action();
        String describe = annotation.describe();

        try {
            // 执行方法
            result = joinPoint.proceed();
            // 记 录 日 志
            context.record(title, describe, action, true, null,null);
        }catch (Exception e){
            // 堆 栈 信 息
            e.printStackTrace();
            // 异 常 记 录
            context.record(title, describe, action, true, null, null);
        }
        return result;
    }

    /**
     * 获 取 注 解
     * */
    public SysLog getAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        SysLog targetSysLog = targetClass.getAnnotation(SysLog.class);
        if ( targetSysLog != null) {
            return targetSysLog;
        } else {
            Method method = signature.getMethod();
            SysLog sysLog = method.getAnnotation(SysLog.class);
            return sysLog;
        }
    }
}