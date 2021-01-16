package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Describe: 权限领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysPower")
@TableName(value="sys_power")
public class SysPower extends BaseDomain {

    /**
     * 权限编号
     * */
    @TableId("id")
    private String id;

    /**
     * 权限名称
     * */
    @TableField("name")
    private String name;

    /**
     * 权限标识
     * */
    @TableField("code")
    private String code;

    /**
     * 权限类型
     * */
    @TableField("type")
    private String type;

    /**
     * 图标
     * */
    @TableField("icon")
    private String icon;

    /**
     * 路由
     * */
    @TableField("path")
    private String path;

    /**
     * 菜单标题
     * */
    @TableField("title")
    private String title;

    /**
     * 启用
     * */
    @TableField("enable")
    private String enable;

    /**
     * 父级
     * */
    @TableField("parent")
    private String parent;

    /**
     * 排序
     * */
    @TableField("sort")
    private Integer sort;

}
