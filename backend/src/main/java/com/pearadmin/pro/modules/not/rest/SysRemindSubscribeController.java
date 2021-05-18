package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.tools.support.server.server.Sys;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.not.param.SysRemindSubscribeRequest;
import com.pearadmin.pro.modules.not.service.SysRemindSubscribeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订阅控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"订阅"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "remind/subscribe")
public class SysRemindSubscribeController extends BaseController {

    @Resource
    private SysRemindSubscribeService sysRemindSubscribeService;

    @GetMapping("page")
    public Result page(SysRemindSubscribeRequest request) {
        return success(sysRemindSubscribeService.page(request));
    }
}
