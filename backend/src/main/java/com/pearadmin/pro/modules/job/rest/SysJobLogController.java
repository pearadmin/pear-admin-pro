package com.pearadmin.pro.modules.job.rest;

import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.job.param.SysJobLogRequest;
import com.pearadmin.pro.modules.job.service.SysJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 任务日志控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/15
 * */
@Api(tags = {"日志"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_JOB + "log")
public class SysJobLogController extends BaseController {

    @Resource
    private SysJobLogService sysJobLogService;

    @GetMapping("page")
    @ApiOperation(value = "任务日志")
    @SysLog(title = "查询日志", describe = "获取任务日志")
    public Result page(SysJobLogRequest request){
        return success(sysJobLogService.page(request));
    }
}