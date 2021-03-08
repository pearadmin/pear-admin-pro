package com.pearadmin.pro.modules.sys.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.TreeDomain;

/**
 * Describe: 部门领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysDept")
@TableName("sys_dept")
public class SysDept extends TreeDomain<SysDept> {

    /**
     * 部门 ID
     * */
    @TableId
    private String id;

    /**
     * 部门名称
     * */
    @TableField("name")
    private String name;

    /**
     * 显示顺序
     * */
    @TableField("sort")
    private Integer sort;

    /**
     * 是否启用
     * */
    @TableField("enable")
    private boolean enable;

}
