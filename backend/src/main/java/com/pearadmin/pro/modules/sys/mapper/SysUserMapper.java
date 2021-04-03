package com.pearadmin.pro.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.domain.request.QueryUserRequest;
import com.pearadmin.pro.modules.sys.domain.response.QueryUserResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>{

    /**
     *
     * */
    List<QueryUserResponse> selectList(@Param("request") QueryUserRequest request);
}
