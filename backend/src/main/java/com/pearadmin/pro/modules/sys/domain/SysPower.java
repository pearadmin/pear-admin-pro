package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.BaseDomain;
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

    private String path;

    private String name;

    private String type;

    private String icon;

    private String title;

    private Integer sort;

    private String link;

    private Boolean hidden;

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
