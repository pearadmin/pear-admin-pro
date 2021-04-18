package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Describe: 角色控制器
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/24
 * */
@Api(tags = {"角色"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "role")
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 查询角色列表
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    public Result page(SysRoleRequest request){
        return success(sysRoleService.page(request));
    }


    @PostMapping("add")
    public Result add(){

        return null;
    }

    @PutMapping("edit")
    public Result edit(){

        return null;
    }

}
