package com.pearadmin.pro.modules.sys.controller;

import com.pearadmin.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"缓存"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "redis")
public class SysRedisController {

}
