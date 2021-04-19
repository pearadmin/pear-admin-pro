package com.pearadmin.pro.modules.sys.rest;

import io.swagger.annotations.Api;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
import com.pearadmin.pro.modules.sys.service.SysFileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import javax.annotation.Resource;

@Api(tags = {"文件"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "file")
public class SysFileController extends BaseController {

    @Resource
    private SysFileService sysFileService;

    /**
     * 查询文件列表
     *
     * @param request 查询参数
     */
    public Result page(SysFileRequest request){
        return success(sysFileService.page(request));
    }
}
