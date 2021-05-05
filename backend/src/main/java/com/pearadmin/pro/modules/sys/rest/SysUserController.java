package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.modules.sys.param.SysUserRoleRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.common.context.UserContext;
import com.pearadmin.pro.modules.sys.param.SysUserRequest;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import com.pearadmin.pro.common.web.base.BaseController;
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
     * 新增用户
     *
     * @param sysUser 用户实体
     */
    @PostMapping("save")
    @SysLog(title = "用户新增")
    @ApiOperation(value = "用户新增")
    public Result save(@RequestBody SysUser sysUser){
        return auto(sysUserService.save(sysUser));
    }

    /**
     * 修改用户
     *
     * @param sysUser 用户实体
     */
    @PutMapping("edit")
    @SysLog(title = "用户修改")
    @ApiOperation(value = "用户修改")
    public Result edit(@RequestBody SysUser sysUser){
        return auto(sysUserService.updateById(sysUser));
    }

    /**
     * 查询用户
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
     * 查询用户
     *
     * @param request 查询参数
     */
    @GetMapping("list")
    @SysLog(title = "用户列表")
    @ApiOperation(value = "用户列表")
    public Result list(SysUserRequest request){ return success(sysUserService.list(request)); }

    /**
     * 分配角色
     *
     * @param request 参数实体
     * */
    @PostMapping("give")
    @SysLog(title = "分配角色")
    @ApiOperation(value = "分配角色")
    public Result give(@RequestBody SysUserRoleRequest request){
        return success(sysUserService.give(request.getUserId(),request.getRoleIds()));
    }

    /**
     * 删除用户
     *
     * @param id 用户编号
     */
    @DeleteMapping("remove")
    @SysLog(title = "用户删除")
    @ApiOperation(value = "用户删除")
    public Result remove(@RequestParam String id){
        return auto(sysUserService.removeById(id));
    }

    /**
     * 批量删除
     *
     * @param ids 用户编号
     */
    @DeleteMapping("removeBatch")
    @SysLog(title = "批量删除")
    @ApiOperation(value = "批量删除")
    public Result removeBatch(@RequestParam List<String> ids) { return auto(sysUserService.removeByIds(ids)); }

    /**
     * 个人资料
     */
    @GetMapping("profile")
    @SysLog(title = "个人资料")
    @ApiOperation(value = "个人资料")
    public Result profile(){
        return success(userContext.getPrincipal());
    }

    /**
     * 用户详情
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

    /**
     * 获取用户角色
     *
     * @param userId 用户编号
     * */
    @GetMapping("role")
    @ApiOperation(value = "用户角色")
    public Result role(String userId){
        return success(sysUserService.role(userId));
    }

    /**
     * 获取用户权限
     *
     * @param userId 用户编号
     * */
    @GetMapping("power")
    @ApiOperation(value = "用户权限")
    public Result power(String userId){
        return success(sysUserService.power(userId));
    }
}
