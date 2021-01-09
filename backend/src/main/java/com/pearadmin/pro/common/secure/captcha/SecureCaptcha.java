package com.pearadmin.pro.common.secure.captcha;

import lombok.Data;

/**
 * Describe: Captcha 验证码实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class SecureCaptcha {

    /**
     * 标识
     * */
    private String key;

    /**
     * 验证码
     * */
    private String code;

    /**
     * 验证图片
     * */
    private String image;

    /**
     * 构造方法
     * */
    public SecureCaptcha(String key, String code, String image){
        this.key = key;
        this.code = code;
        this.image = image;
    }
}
