package com.pearadmin.pro.modules.job.validation;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 任务模型 - 参数校监
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Data
public class SysJobValid {

    /**
     * 名称
     * */
    @NotNull(message = "账户不能为空")
    private String name;

    /**
     * 标识
     */
    @NotNull(message = "账户不能为空")
    private String code;
}
