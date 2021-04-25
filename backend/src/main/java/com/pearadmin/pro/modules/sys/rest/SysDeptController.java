package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.modules.sys.domain.SysDept;
import io.swagger.annotations.Api;
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

    @GetMapping("tree")
    public Result tree(){
        return success(sysDeptService.tree());
    }

    @PostMapping("add")
    public Result add(@RequestBody SysDept sysDept){
        return auto(sysDeptService.save(sysDept));
    }

    @PutMapping("edit")
    public Result edit(@RequestBody SysDept sysDept){
        return auto(sysDeptService.updateById(sysDept));
    }

    @DeleteMapping("remove")
    public Result remove(List<String> deptId){
        return auto(sysDeptService.removeByIds(deptId));
    }
}