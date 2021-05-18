package com.pearadmin.pro.modules.not.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId
    private String id;

    /** 发送者 编号 */
    @TableField("sender_id")
    private String senderId;

    /** 发送者 名称 */
    @TableField(exist = false)
    private String senderName;

    /** 接收者 编号 */
    @TableField("recipient_id")
    private String recipientId;

    /** 接收者 名称 */
    @TableField(exist = false)
    private String recipientName;

    /** 内容 */
    @TableField("content")
    private String content;

    /** 是否阅读 */
    @TableField("is_read")
    private Boolean isRead;

    /** 阅读时间 */
    @TableField("read_time")
    private LocalDateTime readTime;

}
