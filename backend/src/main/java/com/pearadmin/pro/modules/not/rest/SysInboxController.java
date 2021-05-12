package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"私信"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "inbox")
public class SysInboxController {
}
