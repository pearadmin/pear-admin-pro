package com.pearadmin.pro.common.web.exception.auth.token;

import org.springframework.security.core.AuthenticationException;

/**
 * Describe: TOKEN 异常类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class TokenException extends AuthenticationException {

    public TokenException(String message){
        super(message);
    }
}