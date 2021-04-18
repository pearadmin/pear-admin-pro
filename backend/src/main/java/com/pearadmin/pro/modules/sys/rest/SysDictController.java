package com.pearadmin.pro.modules.sys.rest;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pearadmin.pro.modules.sys.param.SysDictRequest;
import com.pearadmin.pro.modules.sys.service.SysDictService;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import javax.annotation.Resource;

/**
 * 数据字典类型控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/28
 * */
@Api(tags = {"字典"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dictType")
public class SysDictController extends BaseController {

    @Resource
    private SysDictService sysDictTypeService;

    /**
     * 查询字典类型列表
     *
     * @param request 查询参数
     */
    @GetMapping("page")
    public Result page(SysDictRequest request){
       return success(sysDictTypeService.page(request));
    }
}