package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.not.param.SysRemindRequest;
import com.pearadmin.pro.modules.not.service.SysRemindService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 提醒控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"提醒"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "remind")
public class SysRemindController extends BaseController {

    @Resource
    private SysRemindService sysRemindService;

    @GetMapping("page")
    public Result page(SysRemindRequest request) {
        return success(sysRemindService.page(request));
    }

}
