package com.pearadmin.pro.modules.sys.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;

/**
 * 岗位模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Data
@Alias("SysPost")
public class SysPost extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
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
