package com.pearadmin.pro.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.domain.request.QueryConfigRequest;
import com.pearadmin.pro.modules.sys.service.SysConfigService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "config")
public class SysConfigController extends BaseController {

    @Resource
    private SysConfigService sysConfigService;

    @GetMapping("page")
    public Result page(Page page, QueryConfigRequest request){
        IPage<SysUser> pageInfo = sysConfigService.lambdaQuery()
                .eq(Strings.isNotBlank(request.getName()), SysConfig::getName,request.getName())
                .eq(Strings.isNotBlank(request.getKey()),SysConfig::getKey,request.getKey())
                .page(page);
        return success(pageInfo);
    }
}
