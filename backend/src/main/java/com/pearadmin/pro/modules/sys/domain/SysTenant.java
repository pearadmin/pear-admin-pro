package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 租户领域模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Data
@Alias("SysTenant")
public class SysTenant extends BaseDomain {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 名称
     * */
    @TableField("name")
    private String name;

    /**
     * 描述
     * */
    @TableField("`describe`")
    private String describe;
}
