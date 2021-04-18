package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
import com.pearadmin.pro.modules.sys.service.SysFileService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = {"文件"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "file")
public class SysFileController extends BaseController {

    @Resource
    private SysFileService sysFileService;

    public Result page(SysFileRequest request){
        return success(sysFileService.page(request));
    }
}
