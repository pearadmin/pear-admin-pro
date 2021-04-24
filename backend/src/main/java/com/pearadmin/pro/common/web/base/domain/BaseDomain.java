package com.pearadmin.pro.common.web.base.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base 实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
public class BaseDomain implements Serializable {

    /**
     * 创建人
     * */
    @TableField("create_by")
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 创建时间
     * */
    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 修改人
     * */
    @TableField("update_by")
    @ApiModelProperty("修改人")
    private String updateBy;

    /**
     * 修改时间
     * */
    @TableField("update_time")
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 删除
     * */
    @TableField("deleted")
    @ApiModelProperty("逻辑删除")
    private boolean deleted;

    /**
     * 备注
     * */
    @TableField("remark")
    @ApiModelProperty("备注")
    private String remark;

}
