package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

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
