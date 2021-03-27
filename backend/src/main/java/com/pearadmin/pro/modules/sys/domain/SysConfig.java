package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Describe: 配置模型
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Data
@Alias("SysConfig")
@TableName("sys_config")
public class SysConfig extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 名称
     * */
    @TableField("name")
    private String name;

    /**
     * 键
     * */
    @TableField("`key`")
    private String key;

    /**
     * 值
     * */
    @TableField("value")
    private String value;

}
