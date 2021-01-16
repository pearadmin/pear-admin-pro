package com.pearadmin.pro.modules.ops.controller;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.secure.services.SecureUserTokenService;
import com.pearadmin.pro.modules.ops.domain.OpsToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(ControllerConstant.PREFIX_OPS + "token")
public class OpsTokenController {

    @Resource
    private SecureUserTokenService secureUserTokenService;

    @GetMapping("list")
    public void list(){

    }
}
