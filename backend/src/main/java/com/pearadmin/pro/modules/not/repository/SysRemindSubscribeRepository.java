package com.pearadmin.pro.modules.not.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.not.domain.SysRemindSubscribe;
import com.pearadmin.pro.modules.not.param.SysRemindSubscribeRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysRemindSubscribeRepository extends BaseMapper<SysRemindSubscribe> {

     List<SysRemindSubscribe> selectRemindSubscribe(SysRemindSubscribeRequest request);
}
