package com.pearadmin.pro.modules.sys.controller;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */

@Api(tags = {"字典"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "dict")
public class SysDictController extends BaseController {

}
