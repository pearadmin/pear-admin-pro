package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.interceptor.enums.Scope;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import org.apache.ibatis.type.Alias;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 角色模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
@Alias("SysRole")
public class SysRole extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 名称
     * */
    @TableField("name")
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 标识
     * */
    @TableField("code")
    @NotBlank(message = "标识不能为空")
    private String code;

    /**
     * 启用
     * */
    @TableField("enable")
    private boolean enable;

    /**
     * 排序
     * */
    @TableField("sort")
    private Integer sort;

    /**
     * 数据权限
     * */
    @TableField("scope")
    private Scope scope;

}
