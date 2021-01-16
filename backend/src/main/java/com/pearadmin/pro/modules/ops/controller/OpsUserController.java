package com.pearadmin.pro.modules.ops.controller;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.secure.services.SecureUser;
import com.pearadmin.pro.common.secure.services.SecureUserTokenService;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.ops.domain.OpsUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(ControllerConstant.PREFIX_OPS + "user")
public class OpsUserController extends BaseController {

    @Resource
    private SecureUserTokenService secureUserTokenService;

    @GetMapping("list")
    public Result list(){
        return success(secureUserTokenService.selectToken());
    }

}
