package com.pearadmin.pro.modules.not.domain;

import com.baomidou.mybatisplus.annotation.TableName;
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
public class SysRemindTemplate {

    /** 编号 */
    private String id;

    /** 动作 */
    private String action;

    /** 模板
     * @param source 源头
     * @param target 目标
     * */
    private String template;

}
