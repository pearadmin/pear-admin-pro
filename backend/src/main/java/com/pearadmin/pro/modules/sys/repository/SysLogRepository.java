package com.pearadmin.pro.modules.sys.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.modules.sys.param.SysLogRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogRepository extends BaseMapper<SysLog> {

    /**
     * 获取日志列表
     *
     * @param request 查询参数
     *
     * @return {@link SysLog}
     * */
    List<SysLog> selectList(@Param("request") SysLogRequest request);

}
