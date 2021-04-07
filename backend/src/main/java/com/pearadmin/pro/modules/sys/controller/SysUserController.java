package com.pearadmin.pro.modules.sys.controller;

import com.pearadmin.pro.modules.sys.param.SysUserRequest;
import com.pearadmin.pro.modules.sys.validation.SysUserValid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import com.pearadmin.pro.common.web.base.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
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

    /**
     * 服务注入
     * */
    @Resource
    private SysUserService sysUserService;

    /**
     * 新增用户信息
     *
     * @param sysUserValid 参数验证
     * @param sysUser 用户实体
     */
    @PostMapping("add")
    public Result add(@RequestBody @Validated SysUserValid sysUserValid,
                      @RequestBody SysUser sysUser){

        return success();
    }

    /**
     * 修改用户信息
     *
     * @param sysUserValid 参数验证
     * @param sysUser 用户实体
     * */
    @PutMapping("edit")
    public Result edit(@RequestBody @Validated SysUserValid sysUserValid, @RequestBody SysUser sysUser){

        return success();
    }

    /**
     * 查询用户列表
     *
     * @param request 查询参数
     * */
    @GetMapping("page")
    public Result page(SysUserRequest request){
        return success(sysUserService.page(request));
    }

    /**
     * 查询用户列表
     *
     * @param request 查询参数
     * */
    @GetMapping("list")
    public Result list(SysUserRequest request){ return success(sysUserService.list(request)); }

    /**
     * 获取用户菜单
     *
     * @return {@link Result}
     * */
    @GetMapping("menu")
    @ApiOperation(value = "用户菜单")
    public Result menu(){
        List<SysPower> menus = new ArrayList<>();
        menus.add(new SysPower( "", "/account", "account", "dir", "", "", 0, "", true, true));
        menus.add(new SysPower( "account", "center", "account-center", "menu", "DashboardOutlined", "个人中心", 0, "", false, true));
        menus.add(new SysPower( "", "/dashboard", "dashboard", "dir", "HomeOutlined", "工作空间", 1, "", false, true));
        menus.add(new SysPower( "dashboard", "console", "dashboard-console", "menu", "DashboardOutlined", "分析页", 0, "", false, true));
        menus.add(new SysPower( "", "/list", "list", "dir", "StockOutlined", "系统监控", 3, "", false, true));
        menus.add(new SysPower( "list", "onlineList", "online-list", "menu", "DatabaseOutlined", "在线用户", 0, "", false, true));
        menus.add(new SysPower( "list", "redisList", "redis-list", "menu", "DatabaseOutlined", "缓存监控", 0, "", false, true));
        menus.add(new SysPower( "list", "docList", "doc-list", "menu", "DatabaseOutlined", "接口文档", 0, "", false, true));
        menus.add(new SysPower( "list", "serverList", "server-list", "menu", "DatabaseOutlined", "运行环境", 0, "", false, true));
        menus.add(new SysPower( "", "/sys", "sys", "dir", "SettingOutlined", "系统管理", 3, "", false, true));
        menus.add(new SysPower( "sys", "/user/index", "user-list", "menu", "DatabaseOutlined", "用户列表", 0, "", false, true));
        menus.add(new SysPower( "sys", "/role/index", "role-list", "menu", "DatabaseOutlined", "角色列表", 0, "", false, true));
        menus.add(new SysPower( "sys", "/power/index", "power-list", "menu", "DatabaseOutlined", "权限列表", 0, "", false, true));
        menus.add(new SysPower( "sys", "/dict/index", "dict-list", "menu", "DatabaseOutlined", "数据字典", 0, "", false, true));
        menus.add(new SysPower( "sys", "/config/index", "config-list", "menu", "DatabaseOutlined", "配置中心", 0, "", false, true));
        menus.add(new SysPower( "sys", "/post/index", "post-list", "menu", "DatabaseOutlined", "岗位列表", 0, "", false, true));
        menus.add(new SysPower( "sys", "/dept/index", "dept-list", "menu", "DatabaseOutlined", "部门列表", 0, "", false, true));
        menus.add(new SysPower( "list", "/log/operate", "operate-list", "menu", "DatabaseOutlined", "操作日志", 0, "", false, true));
        menus.add(new SysPower( "list", "/log/login", "login-list", "menu", "DatabaseOutlined", "登录日志", 0, "", false, true));
        return success("用户菜单信息",menus);
    }

}
