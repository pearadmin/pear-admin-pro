package com.pearadmin.pro.modules.sys.domain;

import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 用户角色模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysUserRole")
public class SysUserRole extends BaseDomain {

    /**
     * 编号
     * */
    private String id;

    /**
     * 用户编号
     * */
    private String userId;

    /**
     * 角色编号
     * */
    private String roleId;
}
