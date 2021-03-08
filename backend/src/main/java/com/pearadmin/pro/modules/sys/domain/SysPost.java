package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Describe: 岗位领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysPost")
@TableName("sys_post")
public class SysPost extends BaseDomain {

    /**
     * 编号
     * */
    @TableId
    private String id;

    /**
     * 编码
     * */
    @TableField("code")
    private String code;

    /**
     * 名称
     * */
    @TableField("name")
    private String name;

    /**
     * 排序
     * */
    @TableField("sort")
    private Integer sort;

    /**
     * 启用
     * */
    @TableField("enable")
    private Boolean enable;

}
