package com.pearadmin.pro.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

    List<SysDept> selectDeptByRoleId(String roleId);

    List<SysDept> selectDeptByParentId(String parentId);

}
