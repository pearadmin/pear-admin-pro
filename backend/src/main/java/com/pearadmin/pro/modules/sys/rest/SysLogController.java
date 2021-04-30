package com.pearadmin.pro.modules.sys.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.modules.sys.param.SysLogRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.pearadmin.pro.modules.sys.service.SysLogService;
import com.pearadmin.pro.common.constant.ControllerConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import javax.annotation.Resource;

/**
 * 日志控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"日志"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "log")
public class SysLogController extends BaseController {

    @Resource
    private SysLogService sysLogService;

    /**
     * 查询操作日志
     *
     * @param request 查询参数
     */
    @GetMapping("operate")
    @SysLog(title = "操作日志")
    @ApiOperation(value = "操作日志")
    public Result operate(SysLogRequest request){
        return success(sysLogService.page(request));
    }

    /**
     * 查询登录日志
     *
     * @param request 查询参数
     */
    @GetMapping("login")
    @SysLog(title = "登录日志")
    @ApiOperation(value = "登录日志")
    public Result login(SysLogRequest request){
        return success(sysLogService.page(request));
    }

    /**
     * 清空日志
     */
    @DeleteMapping("clean")
    @SysLog(title = "清空日志")
    @ApiOperation(value = "清空日志")
    public Result clean(){

        return null;
    }
}
