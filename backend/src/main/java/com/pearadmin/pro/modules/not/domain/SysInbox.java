package com.pearadmin.pro.modules.not.domain;

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
    private String id;

    /**
     * 对话编号
     * */
    private String dialogueId;

    /**
     * 发送者
     * */
    private String senderId;

    /**
     * 接受者
     * */
    private String recipientId;

    /**
     * 消息内容
     * */
    private String content;

    /**
     * 标记
     * */
    private Boolean isRead;

    /**
     * 发送时间
     * */
    private LocalDateTime sendTime;

    /**
     * 阅读时间
     * */
    private LocalDateTime readTime;

}
