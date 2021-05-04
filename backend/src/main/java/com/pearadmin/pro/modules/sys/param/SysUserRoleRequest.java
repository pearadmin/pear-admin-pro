package com.pearadmin.pro.modules.sys.param;

import lombok.Data;

import java.util.List;

@Data
public class SysUserRoleRequest {

    private String userId;

    private List<String> roleIds;
}
