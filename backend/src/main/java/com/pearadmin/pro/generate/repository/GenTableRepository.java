package com.pearadmin.pro.generate.repository;

import com.pearadmin.pro.generate.domain.GenTable;
import com.pearadmin.pro.generate.param.GenTableRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 业务表接口
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Mapper
public interface GenTableRepository {

    /**
     * 查询业务列表
     *
     * @param request 业务信息
     * @return 业务集合
     */
    List<GenTable> selectGenTableList(GenTableRequest request);

    /**
     * 查询据库列表
     *
     * @param request 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTableRequest request);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(String id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    List<GenTable> selectTableByName(String tableName);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTable selectGenTableByName(String tableName);

    /**
     * 新增业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    int insertGenTable(GenTable genTable);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    int updateGenTable(GenTable genTable);

    /**
     * 批量删除业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteGenTableByIds(String[] ids);
}