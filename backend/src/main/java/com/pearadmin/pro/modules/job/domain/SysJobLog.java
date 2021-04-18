package com.pearadmin.pro.modules.job.domain;

import lombok.Data;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import org.apache.ibatis.type.Alias;

/**
 * 任务日志模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
@Alias("SysJobLog")
public class SysJobLog extends BaseDomain {

    /**
     * 编号
     * */
    private String id;

    /**
     * 任务编号
     * */
    private String jobId;

    /**
     * 状态
     * */
    private String status;

    /**
     * 错误信息
     * */
    private String error;

    /**
     * 运行时长
     * */
    private String time;

}
