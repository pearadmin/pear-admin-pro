package com.pearadmin.pro.modules.not.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.ibatis.type.Alias;
import java.time.LocalDateTime;

/**
 * 私人消息
 *
 * @author: 就眠仪式
 * @date: 2021-05-12
 * */
@Data
@Alias("SysInbox")
@TableName("sys_inbox")
public class SysInbox {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 对话编号
     * */
    @TableField("dialogue_id")
    private String dialogueId;

    /**
     * 发送者
     * */
    @TableField("sender_id")
    private String senderId;

    /**
     * 接受者
     * */
    @TableField("recipient_id")
    private String recipientId;

    /**
     * 消息内容
     * */
    @TableField("content")
    private String content;

    /**
     * 标记
     * */
    @TableField("is_read")
    private Boolean isRead;

    /**
     * 发送时间
     * */
    @TableField("send_time")
    private LocalDateTime sendTime;

    /**
     * 阅读时间
     * */
    @TableField("read_time")
    private LocalDateTime readTime;

}
