package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Describe: 日志查询参数实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class QueryLogRequest {

    /**
     * 操作人
     * */
    private String operator;

    /**
     * 开始时间
     * */
    private LocalDateTime startDateTime;

    /**
     * 结束时间
     * */
    private LocalDateTime endDateTime;
}
