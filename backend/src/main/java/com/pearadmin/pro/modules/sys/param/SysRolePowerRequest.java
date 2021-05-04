package com.pearadmin.pro.modules.sys.param;

import lombok.Data;

import java.util.List;

@Data
public class SysRolePowerRequest {

    private String roleId;

    private List<String> powerIds;
}
