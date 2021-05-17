package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"订阅"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "remind/template")
public class SysRemindTemplateController {

}
