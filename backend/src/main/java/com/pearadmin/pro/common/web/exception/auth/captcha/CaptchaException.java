package com.pearadmin.pro.common.web.exception.auth.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * Describe: 验证码异常基类
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 * */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String message){
        super(message);
    }

}
