package com.pearadmin.pro.modules.not.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.not.domain.SysRemindTemplate;
import com.pearadmin.pro.modules.not.param.SysRemindTemplateRequest;
import com.pearadmin.pro.modules.not.service.SysRemindTemplateService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模板控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/28
 * */
@Api(tags = {"模板"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "remind/template")
public class SysRemindTemplateController extends BaseController {

    @Resource
    private SysRemindTemplateService sysRemindTemplateService;

    /**
     * 查询订阅模板
     * <p>
     * @param request 查询参数
     *
     * @return {@link Result}
     */
    @GetMapping("page")
    public Result page(SysRemindTemplateRequest request) {
        return success(sysRemindTemplateService.page(request));
    }

    /**
     * 保存订阅模板
     * <p>
     * @param sysRemindTemplate 模板实体
     *
     * @return {@link Result}
     */
    @PostMapping("save")
    public Result save(@RequestBody SysRemindTemplate sysRemindTemplate) { return success(sysRemindTemplateService.save(sysRemindTemplate)); }

    /**
     * 修改订阅模板
     * <p>
     * @param sysRemindTemplate 模板实体
     *
     * @return {@link Result}
     */
    @PutMapping("edit")
    public Result edit(@RequestBody SysRemindTemplate sysRemindTemplate) { return success(sysRemindTemplateService.updateById(sysRemindTemplate)); }

    /**
     * 删除订阅模板
     * <p>
     * @param id 模板编号
     *
     * @return {@link Result}
     */
    @DeleteMapping("remove")
    public Result remove(@RequestParam String id) {
        return success(sysRemindTemplateService.removeById(id));
    }

    /**
     * 删除订阅模板
     * <p>
     * @param ids 模板编号
     *
     * @return {@link Result}
     */
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestParam List<String> ids) { return success(sysRemindTemplateService.removeByIds(ids)); }

}
