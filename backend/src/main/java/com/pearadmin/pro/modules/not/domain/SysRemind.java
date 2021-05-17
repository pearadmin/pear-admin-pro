package com.pearadmin.pro.modules.not.domain;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import org.apache.ibatis.type.Alias;
import java.time.LocalDateTime;

/**
 * 系统提醒
 * <p>
 * author: 就眠仪式
 * date: 2021-05-12
 * */
@Data
@Alias("SysRemind")
@TableName("sys_remind")
public class SysRemind extends BaseDomain {

    /** 编号 */
    private String id;

    /** 发送者 编号 */
    private String senderId;

    /** 发送者 名称 */
    private String senderName;

    /** 目标 编号 */
    private String objectId;

    /** 目标 信息 */
    private Object object;

    /** 动作 - template 模板 --> content */
    private String action;

    /** 接收者 编号 */
    private String recipientId;

    /** 接收者 名称 */
    private String recipientName;

    /** 内容 */
    private String content;

    /** 是否阅读 */
    private Boolean isRead;

    /** 阅读时间 */
    private LocalDateTime readTime;

}
