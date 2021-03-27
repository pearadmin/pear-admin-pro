package com.pearadmin.pro.modules.sys.controller;

import com.pearadmin.pro.common.interceptor.annotation.DataScope;
import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
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

    @GetMapping("page")
    @DataScope
    @Log(title = "查询权限", describe = "获取权限列表")
    public Result page(){
        List<SysPower> menus = new ArrayList<>();
        SysPower menu1 = new SysPower( "", "/dashboard", "dashboard", "dir", "HomeOutlined", "工作空间", 1, "", false, true);
        SysPower menu2 = new SysPower( "dashboard", "console", "dashboard-console", "menu", "DashboardOutlined", "分析页", 0, "", false, true);
        menu1.getChildren().add(menu2);
        menus.add(menu1);

        SysPower menu3 = new SysPower( "", "/sys", "sys", "dir", "UnorderedListOutlined", "系统管理", 3, "", false, true);
        SysPower menu4 = new SysPower( "sys", "/user/index", "user-list", "menu", "DatabaseOutlined", "用户列表", 0, "", false, true);
        SysPower menu5 = new SysPower( "sys", "/role/index", "role-list", "menu", "DatabaseOutlined", "角色列表", 0, "", false, true);
        SysPower menu6 = new SysPower( "sys", "/power/index", "power-list", "menu", "DatabaseOutlined", "权限列表", 0, "", false, true);

        menu3.getChildren().add(menu4);
        menu3.getChildren().add(menu5);
        menu3.getChildren().add(menu6);
        menus.add(menu3);
        return success("用户菜单信息",menus);
    }
}
