package com.pearadmin.pro.common.secure.captcha;

import com.pearadmin.pro.common.web.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import com.pearadmin.pro.common.web.domain.ResultController;
import com.pearadmin.pro.common.constant.ControllerConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * Describe: Captcha Rest 服务
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@RestController
@RequestMapping(ControllerConstant.PREFIX + "captcha")
public class SecureCaptchaRest extends ResultController {

    @Resource
    private SecureCaptchaService customCaptchaService;

    /**
     * Captcha 生成
     * */
    @GetMapping("createCaptcha")
    public Result createCaptcha(){
        return success(customCaptchaService.createCaptcha());
    }
}
