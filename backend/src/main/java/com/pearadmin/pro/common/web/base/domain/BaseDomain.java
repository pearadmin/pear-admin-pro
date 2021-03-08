package com.pearadmin.pro.common.web.base.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     * */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改人
     * */
    @TableField("update_by")
    private String updateBy;

    /**
     * 修改时间
     * */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除
     * */
    @TableField("deleted")
    private boolean deleted;

    /**
     * 备注
     * */
    @TableField("remark")
    private String remark;

}
