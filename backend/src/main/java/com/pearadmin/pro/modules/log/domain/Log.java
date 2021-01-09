package com.pearadmin.pro.modules.log.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.base.BaseDomain;
import lombok.Data;

/**
 * Describe: LOG 实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class Log extends BaseDomain {

    @TableId
    private String id;

    @TableField
    private String title;

    @TableField
    private String content;

    @TableField
    private String param;

    @TableField
    private String method;

    @TableField
    private String type;

    @TableField
    private String href;

    @TableField
    private String time;

    @TableField
    private String address;

    @TableField
    private String browser;

    @TableField
    private String result;

    @TableField
    private String system;

}
