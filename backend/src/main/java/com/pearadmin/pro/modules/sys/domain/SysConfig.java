package com.pearadmin.pro.modules.sys.domain;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import org.apache.ibatis.type.Alias;

/**
 * 配置模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Data
@Alias("SysConfig")
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

    /**
     * 状态
     * */
    @TableField("enable")
    private Boolean enable;

}
