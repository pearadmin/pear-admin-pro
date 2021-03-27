package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 权限模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysPower")
@TableName(value="sys_power")
public class SysPower extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 父级编号
     * */
    @TableField("parent")
    private String parent;

    /**
     * 路由
     * */
    @TableField("path")
    private String path;

    /**
     * 权限名称
     * */
    @TableField("name")
    private String name;

    /**
     * 类型
     * */
    @TableField("type")
    private String type;

    /**
     * 图标
     * */
    @TableField("icon")
    private String icon;

    /**
     * 标题
     * */
    @TableField("title")
    private String title;

    /**
     * 排序
     * */
    @TableField("sort")
    private Integer sort;

    /**
     * 链接
     * */
    @TableField("link")
    private String link;

    /**
     * 隐藏
     * */
    @TableField(exist = false)
    private Boolean hidden;

    /**
     * 状态
     * */
    @TableField("status")
    private Boolean status;

    /**
     * 辅助列
     * */
    @TableField(exist = false)
    private List<SysPower> children = new ArrayList<>();

    /**
     * TODO 临时构造
     * */
    public SysPower(String parent, String path, String name, String type, String icon, String title, Integer sort, String link, Boolean hidden, Boolean status) {
        this.parent = parent;
        this.path = path;
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.title = title;
        this.sort = sort;
        this.link = link;
        this.hidden = hidden;
        this.status = status;
    }

}
