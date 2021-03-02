package com.pearadmin.pro.common.aop;

import com.alibaba.fastjson.JSON;
import com.pearadmin.pro.common.aop.lang.annotation.Log;
import com.pearadmin.pro.common.aop.lang.enums.Action;
import com.pearadmin.pro.common.tools.core.SecureUtil;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.modules.sys.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * Describe: 系统日志实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Aspect
@Component
public class LogAspect {

    @Resource
    private SysLogService sysLogService;

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
        Object result = null;
        SysLog sysLog = new SysLog();

        try {

            // 注 解 解 析
            Log annotation = getAnnotation(joinPoint);
            String value = annotation.value();
            String title = annotation.title();
            Action action = annotation.action();
            String describe = annotation.describe();

            // 填 充 信 息
            sysLog.setTitle(value);
            sysLog.setTitle(title);
            sysLog.setDescribe(describe);
            sysLog.setAction(action);
            sysLog.setOperator(SecureUtil.getNickName());
            sysLog.setType(ServletUtil.getMethod());
            sysLog.setUrl(ServletUtil.getRequestURI());
            sysLog.setBrowser(ServletUtil.getBrowser());
            sysLog.setMethod(joinPoint.getTarget().getClass().getName());
            sysLog.setCreateBy(SecureUtil.getUserId());
            sysLog.setCreateTime(LocalDateTime.now());
            sysLog.setUpdateBy(SecureUtil.getUserId());
            sysLog.setUpdateTime(LocalDateTime.now());
            sysLog.setAddress(ServletUtil.getRemoteHost());

            // 执 行 方 法
            result = joinPoint.proceed();

            // 补 充 结 果
            sysLog.setState(true);
            sysLog.setResult(JSON.toJSONString(result).substring(0,100));

        }catch (Exception e){

            // 异 常 处 理
            sysLog.setState(false);
            sysLog.setError(e.getMessage());

        }finally {

            // 日 志 记 录
            sysLogService.save(sysLog);
        }
        return result;
    }

    /**
     * 获 取 注 解
     * */
    public Log getAnnotation(ProceedingJoinPoint point) {
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