package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDeptRepository extends BaseMapper<SysDept> {

    /**
     * 根据 roleId 获取部门列表
     *
     * @param roleId 角色编号
     * @return {@link SysDept}
     * */
    List<SysDept> selectDeptByRoleId(String roleId);

    /**
     * 根据 parentId 获取部门列表
     *
     * @param parentId 父级编号
     * @return {@link SysDept}
     * */
    List<SysDept> selectDeptByParentId(String parentId);

}