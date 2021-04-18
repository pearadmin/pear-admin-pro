package com.pearadmin.pro.modules.sys.validation;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 岗位模型 - 参数校监
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Data
public class SysPostValid {

    @NotNull(message = "名称不能为空")
    private String name;
}
