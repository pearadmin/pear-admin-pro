package com.pearadmin.pro.modules.sys.controller;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.controller.response.SysMenu;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "power")
public class SysPowerController extends BaseController {

    @GetMapping("page")
    public Result page(){
        List<SysMenu> menus = new ArrayList<>();
        menus.add(new SysMenu( "", "/account", "account", "dir", "", "公共", 0, "", true, true));
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

}
