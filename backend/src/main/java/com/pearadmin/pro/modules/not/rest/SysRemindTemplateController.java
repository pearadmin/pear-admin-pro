package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.not.domain.SysRemindTemplate;
import com.pearadmin.pro.modules.not.param.SysRemindTemplateRequest;
import com.pearadmin.pro.modules.not.service.SysRemindTemplateService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 模板控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"模板"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "remind/template")
public class SysRemindTemplateController extends BaseController {

    @Resource
    private SysRemindTemplateService sysRemindTemplateService;

    @GetMapping("page")
    public Result page(SysRemindTemplateRequest request) {
        return success(sysRemindTemplateService.page(request));
    }
}
