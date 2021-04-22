package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.service.SysPowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 权限控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"权限"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "power")
public class SysPowerController extends BaseController {

    @Resource
    private SysPowerService sysPowerService;

    /**
     * 查询权限
     */
    @GetMapping("tree")
    @SysLog(title = "查询权限")
    @ApiOperation(value = "查询权限")
    public Result tree(){
        return success(sysPowerService.tree());
    }

    /**
     * 新增权限
     *
     * @param sysPower 权限实体
     *
     * @return {@link Boolean}
     */
    @PostMapping("add")
    @SysLog(title = "新增权限")
    @ApiOperation(value = "新增权限")
    public Result add(@RequestBody SysPower sysPower){
        return auto(sysPowerService.save(sysPower));
    }

    /**
     * 修改权限
     *
     * @param sysPower 权限实体
     *
     * @return {@link Boolean}
     */
    @PutMapping("edit")
    @SysLog(title = "修改权限")
    @ApiOperation(value = "修改权限")
    public Result edit(@RequestBody SysPower sysPower){
        return auto(sysPowerService.updateById(sysPower));
    }

    /**
     * 删除权限
     *
     * @param powerId 权限编号
     *
     * @return {@link Boolean}
     */
    @DeleteMapping("remove")
    @SysLog(title = "删除权限")
    @ApiOperation(value = "删除权限")
    public Result remove(List<String> powerId){
        return auto(sysPowerService.removeByIds(powerId));
    }

}
