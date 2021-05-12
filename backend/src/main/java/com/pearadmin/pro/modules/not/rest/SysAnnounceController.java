package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.not.domain.SysAnnounce;
import com.pearadmin.pro.modules.not.param.SysAnnounceRequest;
import com.pearadmin.pro.modules.not.service.SysAnnounceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 公告控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"公告"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "announce")
public class SysAnnounceController extends BaseController {

    @Resource
    private SysAnnounceService sysAnnounceService;

    @GetMapping("page")
    public Result page(SysAnnounceRequest request){
        return success(sysAnnounceService.page(request));
    }

    @PostMapping("save")
    public Result save(@RequestBody SysAnnounce sysAnnounce){
        return auto(sysAnnounceService.save(sysAnnounce));
    }

    @PutMapping("edit")
    public Result edit(@RequestBody SysAnnounce sysAnnounce){
        return auto(sysAnnounceService.updateById(sysAnnounce));
    }

    @DeleteMapping("remove")
    public Result remove(@RequestParam String id){
        return auto(sysAnnounceService.removeById(id));
    }

    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestParam List<String> ids){
        return auto(sysAnnounceService.removeByIds(ids));
    }
}
