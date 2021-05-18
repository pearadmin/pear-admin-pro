package com.pearadmin.pro.modules.not.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import com.pearadmin.pro.modules.not.enums.Subscribe;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 订阅提醒 - 提醒模板
 * <p>
 * author: 就眠仪式
 * date: 2021-05-12
 * */
@Data
@Alias("SysRemindTemplate")
@TableName("sys_remind_template")
public class SysRemindTemplate extends BaseDomain {

    /**
     * 编号
     * */
    @TableId
    private String id;

    /**
     * 动作
     * */
    @TableField("subscribe")
    private Subscribe subscribe;

    /**
     * 模板
     * @param source 源头
     * @param target 目标
     * */
    @TableField("template")
    private String template;

    /**
     * 创建人
     * */
    @TableField(exist = false)
    private String createName;

}
