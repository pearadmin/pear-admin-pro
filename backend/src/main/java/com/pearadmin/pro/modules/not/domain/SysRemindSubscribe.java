package com.pearadmin.pro.modules.not.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 系统提醒 - 订阅关系
 *
 * @author: 就眠仪式
 * @date: 2021-05-12
 * */
@Data
@Alias("SysRemindSubcribe")
@TableName("sys_remind_subcribe")
public class SysRemindSubscribe {
}
