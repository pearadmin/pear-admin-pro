package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"提醒"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "remind")
public class SysRemindController extends BaseController {
}
