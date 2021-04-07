package com.pearadmin.pro.modules.sys.validation;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 用户模型 - 参数校监
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Data
public class SysUserValid {

    @NotNull(message = "账户不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;
}
