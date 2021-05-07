package com.pearadmin.pro.modules.job.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.job.param.SysJobRequest;
import com.pearadmin.pro.modules.job.service.SysJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 任务控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/15
 * */
@Api(tags = {"任务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_JOB)
public class SysJobController extends BaseController {

    @Resource
    private SysJobService sysJobService;

    /**
     * 查询任务列表 (分页)
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @Log(title = "查询任务")
    @ApiOperation(value = "查询任务")
    public Result page(SysJobRequest request){
        return success(sysJobService.page(request));
    }
}
