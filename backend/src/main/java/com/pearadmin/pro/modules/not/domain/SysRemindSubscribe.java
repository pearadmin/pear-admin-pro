package com.pearadmin.pro.modules.not.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 系统提醒 - 订阅关系
 * <p>
 * author: 就眠仪式
 * date: 2021-05-12
 * */
@Data
@Alias("SysRemindSubcribe")
@TableName("sys_remind_subcribe")
public class SysRemindSubscribe extends BaseDomain {

    /** 编号 */
    private String id;

    /** 订阅者 */
    private String userId;

    /** 被訂閲者 */
    private String beanId;

    /** 訂閲類型 */
    private String action;

    /**
     * 訂閲关系 0.publish 1.follow
     * */
    private String relation;

}
