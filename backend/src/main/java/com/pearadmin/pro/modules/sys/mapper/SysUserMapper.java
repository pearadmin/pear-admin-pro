package com.pearadmin.pro.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>{

}
