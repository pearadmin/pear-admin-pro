package com.pearadmin.pro.common.web.exception.auth.captcha;

/**
 * Describe: 验 证 码 失 效 异 常
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class CaptchaValidationException extends CaptchaException{

    public CaptchaValidationException(String message){
        super(message);
    }
}
