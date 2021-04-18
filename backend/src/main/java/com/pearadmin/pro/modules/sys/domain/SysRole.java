package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.web.interceptor.enums.Scope;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import org.apache.ibatis.type.Alias;
import lombok.Data;

@Data
@Alias("SysRole")
public class SysRole extends BaseDomain {

    /**
     * 编号
     */
    @TableId("id")
    private String id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 标识
     */
    @TableField("code")
    private String code;

    /**
     * 启用
     */
    @TableField("enable")
    private boolean enable;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 数据权限
     */
    @TableField("scope")
    private Scope scope;

}
