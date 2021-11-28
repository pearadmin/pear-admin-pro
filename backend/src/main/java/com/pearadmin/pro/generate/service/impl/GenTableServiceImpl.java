package com.pearadmin.pro.generate.service.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import com.pearadmin.pro.common.constant.GenerateConstant;
import com.pearadmin.pro.common.constant.SystemConstant;
import com.pearadmin.pro.common.tools.core.SequenceUtil;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.common.web.exception.base.BusinessException;
import com.pearadmin.pro.generate.domain.GenTable;
import com.pearadmin.pro.generate.domain.GenTableColumn;
import com.pearadmin.pro.generate.param.GenTableRequest;
import com.pearadmin.pro.generate.repository.GenTableColumnRepository;
import com.pearadmin.pro.generate.repository.GenTableRepository;
import com.pearadmin.pro.generate.service.IGenTableService;
import com.pearadmin.pro.generate.tools.GenUtil;
import com.pearadmin.pro.generate.tools.GenTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pearadmin.pro.common.tools.core.string.StringUtil;
import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Describe: 业务表服务实现
 * Author: 就眠仪式
 * CreateTime: 2019/10/23
 */
@Slf4j
@Service
public class GenTableServiceImpl implements IGenTableService {

    @Resource
    private GenTableRepository genTableMapper;

    @Resource
    private GenTableColumnRepository genTableColumnMapper;

    /**
     * 获取代码生成地址
     *
     * @param table    业务表信息
     * @param template 模板文件路径
     * @return 生成地址
     */
    public static String getGenPath(GenTable table, String template) {
        String genPath = table.getGenPath();
        if (StringUtil.equals(genPath, SystemConstant.SLASH)) {
            return System.getProperty("user.dir") + File.separator + "src" + File.separator + GenTemplateUtil.getFileName(template, table);
        }
        return genPath + File.separator + GenTemplateUtil.getFileName(template, table);
    }

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    @Override
    public GenTable selectGenTableById(String id) {
        GenTable genTable = genTableMapper.selectGenTableById(id);
        setTableFromOptions(genTable);
        return genTable;
    }

    /**
     * 查询业务列表
     *
     * @param request 业务信息
     * @return 业务集合
     */
    @Override
    public PageResponse<GenTable> selectGenTableList(GenTableRequest request) {
        return Pageable.of(request,()-> genTableMapper.selectGenTableList(request));
    }

    /**
     * 查询据库列表
     *
     * @param request 业务信息
     * @return 数据库表集合
     */
    @Override
    public PageResponse<GenTable> selectDbTableList(GenTableRequest request) {
        return Pageable.of(request,()-> genTableMapper.selectDbTableList(request));
    }

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    @Override
    public List<GenTable> selectDbTableListByNames(String[] tableNames) {
        return genTableMapper.selectDbTableListByNames(tableNames);
    }

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    @Override
    public List<GenTable> selectGenTableAll() {
        return genTableMapper.selectGenTableAll();
    }

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGenTable(GenTable genTable) {
        int row = genTableMapper.updateGenTable(genTable);
        if (row > 0) {
            for (GenTableColumn cenTableColumn : genTable.getColumns()) {
                genTableColumnMapper.updateGenTableColumn(cenTableColumn);
            }
        }
    }

    /**
     * 删除业务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGenTableByIds(String ids) {
        genTableMapper.deleteGenTableByIds(Convert.toStrArray(ids));
        genTableColumnMapper.deleteGenTableColumnByIds(Convert.toStrArray(ids));
    }

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importGenTable(List<GenTable> tableList) {
        for (GenTable table : tableList) {
            GenUtil.initTable(table);
            int row = genTableMapper.insertGenTable(table);
            if (row > 0) {
                List<GenTableColumn> genTableColumns = genTableColumnMapper.selectDbTableColumnsByName(table.getTableName());
                for (GenTableColumn column : genTableColumns) {
                    column.setColumnId(SequenceUtil.makeStringId());
                    GenUtil.initColumnField(column, table);
                    genTableColumnMapper.insertGenTableColumn(column);
                }
            }
        }
    }

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    @Override
    public Map<String, String> previewCode(String tableId) {
        Map<String, String> dataMap = new LinkedHashMap<>();
        GenTable table = genTableMapper.selectGenTableById(tableId);
        setSubTable(table);
        setPkColumn(table);
        GenTemplateUtil.initVelocity();
        VelocityContext context = GenTemplateUtil.prepareContext(table);

        List<String> templates = GenTemplateUtil.getTemplateList(table.getTplCategory());
        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, SystemConstant.UTF8);
            tpl.merge(context, sw);
            dataMap.put(template, sw.toString());
        }
        return dataMap;
    }

    /**
     * 生成代码（下载方式）
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] downloadCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        generatorCode(tableName, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 生成代码（自定义路径）
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public void generatorCode(String tableName) {
        GenTable table = genTableMapper.selectGenTableByName(tableName);

        setSubTable(table);

        setPkColumn(table);

        GenTemplateUtil.initVelocity();

        VelocityContext context = GenTemplateUtil.prepareContext(table);

        List<String> templates = GenTemplateUtil.getTemplateList(table.getTplCategory());

        for (String template : templates) {
            if (!StringUtil.contains(template, "sql.vm")) {
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, SystemConstant.UTF8);
                tpl.merge(context, sw);
                try {
                    String path = getGenPath(table, template);
                    FileUtils.writeStringToFile(new File(path), sw.toString(), SystemConstant.UTF8);
                } catch (IOException e) {
                    throw new BusinessException("渲染模板失败，表名：" + table.getTableName());
                }
            } else if (StringUtil.contains(template, "sql.vm")) {
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, SystemConstant.UTF8);
                tpl.merge(context, sw);
                System.out.println("生成的sql:--------\n" + sw);
            }
        }
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] downloadCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            generatorCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     *
     * @param tableName 表名
     * @param zip       压缩文件流
     */
    private void generatorCode(String tableName, ZipOutputStream zip) {
        GenTable table = genTableMapper.selectGenTableByName(tableName);

        setSubTable(table);
        setPkColumn(table);

        GenTemplateUtil.initVelocity();
        VelocityContext context = GenTemplateUtil.prepareContext(table);

        List<String> templates = GenTemplateUtil.getTemplateList(table.getTplCategory());

        for (String template : templates) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, SystemConstant.UTF8);
            tpl.merge(context, sw);
            try {
                zip.putNextEntry(new ZipEntry(GenTemplateUtil.getFileName(template, table)));
                IOUtils.write(sw.toString(), zip, SystemConstant.UTF8);
                IOUtils.closeQuietly(sw);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                log.error("渲染模板失败，表名：" + table.getTableName(), e);
            }
        }
    }

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    @Override
    public void validateEdit(GenTable genTable) {
        if (GenerateConstant.TPL_TREE.equals(genTable.getTplCategory())) {
            if (StringUtil.isEmpty(genTable.getTreeCode())) {
                throw new BusinessException("树编码字段不能为空");
            } else if (StringUtil.isEmpty(genTable.getTreeParentCode())) {
                throw new BusinessException("树父编码字段不能为空");
            }
        } else if (GenerateConstant.TPL_SUB.equals(genTable.getTplCategory())) {
            if (StringUtil.isEmpty(genTable.getSubTableName())) {
                throw new BusinessException("关联子表的表名不能为空");
            } else if (StringUtil.isEmpty(genTable.getSubTableFkName())) {
                throw new BusinessException("子表关联的外键名不能为空");
            }
        }
    }

    /**
     * 设置主键列信息
     *
     * @param table 业务表信息
     */
    public void setPkColumn(GenTable table) {
        for (GenTableColumn column : table.getColumns()) {
            if (column.isPk()) {
                table.setPkColumn(column);
                break;
            }
        }
        if (StringUtil.isNull(table.getPkColumn())) {
            table.setPkColumn(table.getColumns().get(0));
        }
        if (GenerateConstant.TPL_SUB.equals(table.getTplCategory())) {
            for (GenTableColumn column : table.getSubTable().getColumns()) {
                if (column.isPk()) {
                    table.getSubTable().setPkColumn(column);
                    break;
                }
            }
            if (StringUtil.isNull(table.getSubTable().getPkColumn())) {
                table.getSubTable().setPkColumn(table.getSubTable().getColumns().get(0));
            }
        }
    }

    /**
     * 设置主子表信息
     *
     * @param table 业务表信息
     */
    public void setSubTable(GenTable table) {
        String subTableName = table.getSubTableName();
        if (StringUtil.isNotEmpty(subTableName)) {
            table.setSubTable(genTableMapper.selectGenTableByName(subTableName));
        }
    }

    /**
     * 设置代码生成其他选项值
     *
     * @param genTable 设置后的生成对象
     */
    public void setTableFromOptions(GenTable genTable) {
        JSONObject paramsObj = JSONObject.parseObject(genTable.getOptions());
        if (StringUtil.isNotNull(paramsObj)) {
            String treeCode = paramsObj.getString(GenerateConstant.TREE_CODE);
            String treeParentCode = paramsObj.getString(GenerateConstant.TREE_PARENT_CODE);
            String treeName = paramsObj.getString(GenerateConstant.TREE_NAME);
            String parentMenuId = paramsObj.getString(GenerateConstant.PARENT_MENU_ID);
            String parentMenuName = paramsObj.getString(GenerateConstant.PARENT_MENU_NAME);
            genTable.setTreeCode(treeCode);
            genTable.setTreeParentCode(treeParentCode);
            genTable.setTreeName(treeName);
            genTable.setParentMenuId(parentMenuId);
            genTable.setParentMenuName(parentMenuName);
        }
    }
}