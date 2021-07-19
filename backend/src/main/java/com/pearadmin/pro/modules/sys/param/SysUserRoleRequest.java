package com.pearadmin.pro.modules.sys.param;

import lombok.Data;

import java.util.List;

/**
 * 用户角色 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 */
@Data
public class SysUserRoleRequest {

    private String userId;

    private List<String> roleIds;
}
