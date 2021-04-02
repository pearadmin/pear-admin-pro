package com.pearadmin.pro.modules.job.domain;

import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 任务模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
@Alias("Job")
public class SysJob extends BaseDomain {

    /**
     * 任务编号
     * */
    private String id;

    /**
     * 任务名称
     * */
    private String name;

    /**
     * 运行类
     * */
    private String beanName;

    /**
     * 参数
     * */
    private String params;

    /**
     * 表达式
     * */
    private String cronExpression;

    /**
     * 状态
     * */
    private String status;

    /**
     * 分组
     * */
    private String groupId;
}
