package com.pearadmin.pro.modules.not.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.not.domain.SysRemindTemplate;
import com.pearadmin.pro.modules.not.param.SysRemindTemplateRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRemindTemplateRepository extends BaseMapper<SysRemindTemplate> {

    List<SysRemindTemplate> selectRemindTemplate(SysRemindTemplateRequest request);
}
