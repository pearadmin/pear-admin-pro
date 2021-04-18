package com.pearadmin.pro.modules.sys.domain;

import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 角色部门模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
@Alias("SysRoleDept")
public class SysRoleDept extends BaseDomain {

    /**
     * 编号
     */
    private String id;

    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 部门编号
     */
    private String deptId;

}
