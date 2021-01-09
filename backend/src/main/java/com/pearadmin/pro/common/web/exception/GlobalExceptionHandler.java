package com.pearadmin.pro.common.web.exception;

import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.common.web.exception.base.BusinessException;
import io.lettuce.core.RedisConnectionException;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeAnnos;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

/**
 * Describe: 全局异常处理
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 不 支 持 的 请 求 类 型
     * */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleException(HttpRequestMethodNotSupportedException e){
        return Result.failure("不支持"+e.getMethod()+"请求");
    }

    /**
     * 未 知 的 运 行 时 异 常
     * */
    @ExceptionHandler(RuntimeException.class)
    public Result notFount(RuntimeException e){
        return Result.failure("运行时异常：" + e.getMessage());
    }

    /**
     * 系 统 异 常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e)
    {
        return Result.failure("服务器错误，请联系管理员");
    }

    /**
     * 业 务 异 常
     * */
    @ExceptionHandler(BusinessException.class)
    public Result businessException(HttpServletRequest request, BusinessException e){
        return Result.failure(e.getMessage());
    }
}
