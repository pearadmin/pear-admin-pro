package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

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
     * 编号
     * */
    private String id;

    /**
     * 父级编号
     * */
    private String parent;

    /**
     * 路由
     * */
    private String path;

    /**
     * 权限名称
     * */
    private String name;

    /**
     * 类型
     * */
    private String type;

    /**
     * 图标
     * */
    private String icon;

    /**
     * 标题
     * */
    private String title;

    /**
     * 排序
     * */
    private Integer sort;

    /**
     * 链接
     * */
    private String link;

    /**
     * 隐藏
     * */
    private Boolean hidden;

    /**
     * 状态
     * */
    private Boolean status;

    private List<SysPower> children = new ArrayList<>();

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
