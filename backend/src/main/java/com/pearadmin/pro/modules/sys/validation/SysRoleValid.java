package com.pearadmin.pro.modules.sys.validation;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 角色模型 - 参数校监
 *
 * Author: 就眠仪式
 * CreateTime: 2021-03-27
 */
@Data
public class SysRoleValid {

    @NotNull(message = "名称不能为空")
    private String name;

    @NotNull(message = "标识不能为空")
    private String code;

}
