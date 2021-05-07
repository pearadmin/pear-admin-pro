package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.Log;
import com.pearadmin.pro.modules.sys.domain.SysFile;
import io.swagger.annotations.Api;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
import com.pearadmin.pro.modules.sys.service.SysFileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("page")
    @Log(title = "文件列表")
    @ApiOperation(value = "文件列表")
    public Result page(SysFileRequest request){
        return success(sysFileService.page(request));
    }

    /**
     * 文件上传
     *
     * @param multipartFile 文件本身
     */
    @GetMapping("upload")
    @Log(title = "文件上传")
    @ApiOperation(value = "文件上传")
    public Result upload(MultipartFile multipartFile) {


        return null;
    }

}
