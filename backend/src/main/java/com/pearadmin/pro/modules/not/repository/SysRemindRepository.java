package com.pearadmin.pro.modules.not.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.not.domain.SysRemind;
import com.pearadmin.pro.modules.not.param.SysRemindRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRemindRepository extends BaseMapper<SysRemind> {

    List<SysRemind> selectRemind(@Param("request") SysRemindRequest remindRequest);
}
