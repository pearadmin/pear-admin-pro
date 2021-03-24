package com.pearadmin.pro.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.aop.enums.Action;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.domain.request.QueryLogRequest;
import com.pearadmin.pro.modules.sys.service.SysLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "log")
public class SysLogController extends BaseController {

    @Resource
    private SysLogService sysLogService;

    @GetMapping("operate")
    @Log(title = "查询日志", describe = "获取操作日志")
    public Result operate(Page page, QueryLogRequest request){
        IPage<SysUser> pageInfo = sysLogService.lambdaQuery().ne(com.pearadmin.pro.modules.sys.domain.SysLog::getAction, Action.AUTH).orderByDesc(com.pearadmin.pro.modules.sys.domain.SysLog::getCreateTime).page(page);
        return success(pageInfo);
    }

    @GetMapping("login")
    @Log(title = "查询日志", describe = "获取登录日志")
    public Result login(Page page, QueryLogRequest request){
        IPage<SysUser> pageInfo = sysLogService.lambdaQuery().eq(com.pearadmin.pro.modules.sys.domain.SysLog::getAction, Action.AUTH).orderByDesc(com.pearadmin.pro.modules.sys.domain.SysLog::getCreateTime).page(page);
        return success(pageInfo);
    }
}
