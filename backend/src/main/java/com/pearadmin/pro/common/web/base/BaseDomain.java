package com.pearadmin.pro.common.web.base;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Describe: Base 实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class BaseDomain implements Serializable {

    /**
     * 创建人
     * */
    private String createBy;

    /**
     * 创建时间
     * */
    private LocalDateTime createTime;

    /**
     * 修改人
     * */
    private String updateBy;

    /**
     * 修改时间
     * */
    private LocalDateTime updateTime;

    /**
     * 删除
     * */
    private boolean deleted;

    /**
     * 备注
     * */
    private String remark;

    /**
     * 租户
     * */
    private String tenantId;

    /**
     * 项目
     * */
    private String projectId;

}
