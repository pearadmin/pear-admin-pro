package com.pearadmin.pro.generate.rest;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.tools.core.SequenceUtil;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.generate.domain.GenTable;
import com.pearadmin.pro.generate.domain.GenTableColumn;
import com.pearadmin.pro.generate.param.GenTableImportRequest;
import com.pearadmin.pro.generate.param.GenTableRequest;
import com.pearadmin.pro.generate.service.IGenTableColumnService;
import com.pearadmin.pro.generate.service.IGenTableService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Api(tags = {"代码"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_GEN)
public class GenController extends BaseController {

    @Resource
    private IGenTableService genTableService;

    @Resource
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     *
     * @param request 查询参数
     */
    @GetMapping("/page")
    @ResponseBody
    public Result page(GenTableRequest request) {
        return success(genTableService.selectGenTableList(request));
    }

    /**
     * 查询数据库列表
     *
     * @param request 查询参数
     */
    @GetMapping("/db/page")
    @ResponseBody
    public Result DBPage(GenTableRequest request) {
        return success(genTableService.selectDbTableList(request));
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping("/column/list")
    @ResponseBody
    public Result columnList(GenTableColumn genTableColumn) {
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(genTableColumn);
        return success(list);
    }

    /**
     * 导入表结构（保存）
     */
    @ResponseBody
    @PostMapping("/import/table")
    public Result importTable(@RequestBody GenTableImportRequest request) {
        String[] tableNames = request.getTableNames();
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return success("导入成功");
    }

    /**
     * 修改保存代码生成业务
     *
     * @param genTable 代码生成实体
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result editSave(@Validated GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return success("保存成功");
    }

    /**
     * 根据 编号 删除代码生成配置
     *
     * @param ids 代码生成编号
     */
    @PostMapping("/remove")
    @ResponseBody
    public Result remove(String ids) {
        genTableService.deleteGenTableByIds(ids);
        return success();
    }

    /**
     * 预览代码
     *
     * @param tableId 表格编号
     */
    @GetMapping("/preview/{tableId}")
    @ResponseBody
    public Result preview(@PathVariable("tableId") String tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return success(dataMap);
    }

    /**
     * 下载代码
     *
     * @param tableName 表格名称
     */
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码
     *
     * @param tableName 表格名称
     */
    @GetMapping("/genCode/{tableName}")
    @ResponseBody
    public Result genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return success();
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 下载压缩文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"pearadmin.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}