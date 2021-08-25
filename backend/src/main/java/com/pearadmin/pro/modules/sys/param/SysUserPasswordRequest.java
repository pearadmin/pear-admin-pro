package com.pearadmin.pro.modules.sys.param;

import lombok.Data;

@Data
public class SysUserPasswordRequest {

    /**
     * 用户编号
     * */
    private String userId;

    /**
     * 密码 - 新
     * */
    private String newPassword;

    /**
     * 密码 - 旧
     * */
    private String oldPassword;
}
