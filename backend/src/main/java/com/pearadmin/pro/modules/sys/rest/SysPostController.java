package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysPost;
import com.pearadmin.pro.modules.sys.param.SysPostRequest;
import com.pearadmin.pro.modules.sys.service.SysPostService;
import com.pearadmin.pro.modules.sys.validation.SysPostValid;
import io.swagger.annotations.Api;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 岗位控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"岗位"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "post")
public class SysPostController extends BaseController {

    @Resource
    private SysPostService sysPostService;

    /**
     * 查询岗位列表
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @SysLog(title = "查询岗位")
    @ApiOperation(value = "查询岗位")
    public Result page(SysPostRequest request){
        return success(sysPostService.page(request));
    }

    /**
     * 新增岗位
     *
     * @param sysPostValid 参数验证
     * @param sysPost 角色实体
     *
     * @return {@link Boolean}
     */
    @GetMapping("add")
    @SysLog(title = "新增岗位")
    @ApiOperation(value = "新增岗位")
    public Result add(@RequestBody @Validated SysPostValid sysPostValid,
                      @RequestBody SysPost sysPost){
        return auto(sysPostService.save(sysPost));
    }

    /**
     * 修改岗位
     *
     * @param sysPostValid 参数验证
     * @param sysPost 角色实体
     *
     * @return {@link Boolean}
     */
    @GetMapping("edit")
    @SysLog(title = "修改岗位")
    @ApiOperation(value = "修改岗位")
    public Result edit(@RequestBody @Validated SysPostValid sysPostValid,
                      @RequestBody SysPost sysPost){
        return auto(sysPostService.updateById(sysPost));
    }

}