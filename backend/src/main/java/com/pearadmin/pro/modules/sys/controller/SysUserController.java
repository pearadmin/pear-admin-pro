package com.pearadmin.pro.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.tools.security.SecurityUtil;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.controller.response.SysMenu;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "user")
public class SysUserController extends BaseController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("info")
    public Result info(){
        return success(SecurityUtil.getPrincipal());
    }

    @GetMapping("info/{id}")
    public Result info(@PathVariable("id")String id){
        return success(sysUserService.getById(id));
    }

    @GetMapping("page")
    public Result page(Page pageParam){
        IPage<SysUser> pageInfo = sysUserService.lambdaQuery().page(pageParam);
        return success(pageInfo);
    }

    @GetMapping("list")
    public Result list(){
        List<SysUser> list = sysUserService.lambdaQuery().list();
        return success(list);
    }

    @GetMapping("menu")
    public Result menu(){
        List<SysMenu> menus = new ArrayList<>();
        menus.add(new SysMenu( "", "/account", "account", "dir", "", "", 0, "", true, true));
        menus.add(new SysMenu( "account", "center", "account-center", "menu", "DashboardOutlined", "个人中心", 0, "", false, true));
        menus.add(new SysMenu( "", "/dashboard", "dashboard", "dir", "HomeOutlined", "工作空间", 1, "", false, true));
        menus.add(new SysMenu( "dashboard", "console", "dashboard-console", "menu", "DashboardOutlined", "分析页", 0, "", false, true));
        menus.add(new SysMenu( "", "/list", "list", "dir", "UnorderedListOutlined", "系统监控", 3, "", false, true));
        menus.add(new SysMenu( "list", "ops-user", "ops-user", "menu", "DatabaseOutlined", "在线用户", 0, "", false, true));
        menus.add(new SysMenu( "list", "tableList", "table-list", "menu", "DatabaseOutlined", "缓存监控", 0, "", false, true));
        menus.add(new SysMenu( "", "/sys", "sys", "dir", "UnorderedListOutlined", "系统管理", 3, "", false, true));
        menus.add(new SysMenu( "sys", "/user/index", "user-list", "menu", "DatabaseOutlined", "用户列表", 0, "", false, true));
        menus.add(new SysMenu( "sys", "/role/index", "role-list", "menu", "DatabaseOutlined", "角色列表", 0, "", false, true));
        menus.add(new SysMenu( "sys", "/power/index", "power-list", "menu", "DatabaseOutlined", "权限列表", 0, "", false, true));
        return success("用户菜单信息",menus);
    }

    @PostMapping("add")
    public Result add(@RequestBody SysUser sysUser){
        boolean flag = sysUserService.save(sysUser);
        return decide(flag);
    }

    @PutMapping("edit")
    public Result edit(@RequestBody SysUser sysUser){
        boolean flag = sysUserService.updateById(sysUser);
        return success(flag);
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable("id")String id){
        boolean flag = sysUserService.removeById(id);
        return decide(flag);
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(List<String> ids){
        boolean flag = sysUserService.removeByIds(ids);
        return decide(flag);
    }
}
