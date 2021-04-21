package com.pearadmin.pro.modules.sys.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.modules.sys.param.SysUserRequest;
import com.pearadmin.pro.modules.sys.validation.SysUserValid;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import com.pearadmin.pro.common.web.base.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 用户控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"用户"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private UserContext userContext;

    /**
     * 新增用户信息
     *
     * @param sysUserValid 参数验证
     * @param sysUser 用户实体
     */
    @PostMapping("add")
    @SysLog(title = "用户新增")
    @ApiOperation(value = "用户新增")
    public Result add(@RequestBody @Validated SysUserValid sysUserValid, @RequestBody SysUser sysUser){
        return auto(sysUserService.save(sysUser));
    }

    /**
     * 修改用户信息
     *
     * @param sysUserValid 参数验证
     * @param sysUser 用户实体
     */
    @PutMapping("edit")
    @SysLog(title = "用户修改")
    @ApiOperation(value = "用户修改")
    public Result edit(@RequestBody @Validated SysUserValid sysUserValid, @RequestBody SysUser sysUser){
        return auto(sysUserService.updateById(sysUser));
    }

    /**
     * 查询用户列表
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    @SysLog(title = "用户列表")
    @ApiOperation(value = "用户列表")
    public Result page(SysUserRequest request){
        return success(sysUserService.page(request));
    }

    /**
     * 查询用户列表
     *
     * @param request 查询参数
     */
    @GetMapping("list")
    @SysLog(title = "用户列表")
    @ApiOperation(value = "用户列表")
    public Result list(SysUserRequest request){ return success(sysUserService.list(request)); }

    /**
     * 删除用户列表
     *
     * @param userId 用户编号
     */
    @DeleteMapping("remove")
    @SysLog(title = "用户删除")
    @ApiOperation(value = "用户删除")
    public Result remove(List<String> userId){
        return auto(sysUserService.removeByIds(userId));
    }

    /**
     * 获取当前用户
     */
    @GetMapping("profile")
    @SysLog(title = "个人资料")
    @ApiOperation(value = "个人资料")
    public Result profile(){
        return success(userContext.getPrincipal());
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户编号
     * */
    @GetMapping("info")
    @SysLog(title = "用户详情")
    @ApiOperation(value = "用户详情")
    public Result info(String userId){
        return success(sysUserService.getById(userId));
    }

    /**
     * 获取用户菜单
     *
     * @return {@link Result}
     * */
    @GetMapping("menu")
    @ApiOperation(value = "用户菜单")
    public Result menu(){
        return success(sysUserService.menu(userContext.getUserId()));
    }
}