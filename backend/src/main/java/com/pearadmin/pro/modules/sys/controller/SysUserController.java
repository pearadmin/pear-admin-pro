package com.pearadmin.pro.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import com.pearadmin.pro.common.web.domain.Result;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("page")
    public Result page(IPage pageDomain){ return success(sysUserService.lambdaQuery().page(pageDomain)); }

    @GetMapping("get")
    public Result get(String id){
        return success(sysUserService.getById(id));
    }

    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser){
        return success(sysUserService.save(sysUser));
    }

    @PutMapping("edit")
    public Result edit(@RequestBody SysUser sysUser){
        return success(sysUserService.updateById(sysUser));
    }

    @DeleteMapping("remove")
    public Result remove(String id){
        return success(sysUserService.removeById(id));
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(List<String> ids){
        return success(sysUserService.removeByIds(ids));
    }

}
