package com.pearadmin.pro.modules.sys.domain.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueryLogRequest {

    private String operator;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
