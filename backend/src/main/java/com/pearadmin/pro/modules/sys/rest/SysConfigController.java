package com.pearadmin.pro.modules.sys.rest;

import io.swagger.annotations.Api;
import com.pearadmin.pro.common.web.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import com.pearadmin.pro.modules.sys.param.SysConfigRequest;
import com.pearadmin.pro.modules.sys.service.SysConfigService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import javax.annotation.Resource;

/**
 * 配置控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Api(tags = {"配置"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "config")
public class SysConfigController extends BaseController {

    @Resource
    private SysConfigService sysConfigService;

    /**
     * 查询配置列表
     *
     * @param request 查询参数
     * @return {@link Result}
     */
    @GetMapping("page")
    public Result page(SysConfigRequest request){
        return success(sysConfigService.page(request));
    }
}
