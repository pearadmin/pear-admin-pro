package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.pearadmin.pro.modules.sys.service.SysDeptService;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import javax.annotation.Resource;
import java.util.List;

/**
 * 部门控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"部门"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dept")
public class SysDeptController extends BaseController {

    @Resource
    private SysDeptService sysDeptService;

    /**
     * 查询部门列表
     *
     * @return {@link Result}
     */
    @GetMapping("tree")
    @SysLog(title = "查询部门")
    @ApiOperation(value = "查询部门")
    public Result tree(){
        return success(sysDeptService.tree());
    }

    /**
     * 新增部门
     *
     * @param sysDept 部门实体
     */
    @PostMapping("save")
    @SysLog(title = "新增部门")
    @ApiOperation(value = "新增部门")
    public Result save(@RequestBody SysDept sysDept){
        return auto(sysDeptService.save(sysDept));
    }

    /**
     * 修改部门
     *
     * @param sysDept 部门实体
     */
    @PutMapping("edit")
    @SysLog(title = "修改部门")
    @ApiOperation(value = "修改部门")
    public Result edit(@RequestBody SysDept sysDept){
        return auto(sysDeptService.updateById(sysDept));
    }

    /**
     * 删除部门
     *
     * @param id 部门编号
     */
    @DeleteMapping("remove")
    @SysLog(title = "删除部门")
    @ApiOperation(value = "删除部门")
    public Result remove(String id){
        return auto(sysDeptService.removeById(id));
    }

    /**
     * 删除部门
     *
     * @param ids 部门编号
     */
    @DeleteMapping("removeBatch")
    public Result removeBatch(List<String> ids) { return auto(sysDeptService.removeByIds(ids)); }
}