package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.modules.not.service.SysInboxService;
import io.swagger.annotations.Api;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.not.param.SysInboxRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {"私信"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "inbox")
public class SysInboxController extends BaseController {

    @Resource
    private SysInboxService sysInboxService;

    @GetMapping("page")
    public Result page(SysInboxRequest request){
        return success(sysInboxService.page(request));
    }
}
