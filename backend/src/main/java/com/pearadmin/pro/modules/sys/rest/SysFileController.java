package com.pearadmin.pro.modules.sys.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.pearadmin.pro.common.aop.annotation.Log;
import io.swagger.annotations.Api;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
import com.pearadmin.pro.modules.sys.service.SysFileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
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
     *
     * @return {@link String}
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
     * @param file 上传文件
     *
     * @return {@link String}
     */
    @PostMapping("upload")
    @ApiOperation(value = "文件上传")
    public Result upload(@RequestParam("file") MultipartFile file) {
        if( file.isEmpty()) { return failure(); }
        return success(sysFileService.upload(file));
    }

    /**
     * 文件上传
     *
     * @param request 上传文件
     */
    @PostMapping("multipartUpload")
    @ApiOperation(value = "多文件上传")
    public Result multipartUpload(HttpServletRequest request){
        return success(sysFileService.upload(((MultipartHttpServletRequest) request).getFiles("file")));
    }

}
