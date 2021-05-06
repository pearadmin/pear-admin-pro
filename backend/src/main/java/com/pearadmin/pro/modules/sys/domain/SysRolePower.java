package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 角色权限模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysRolePower")
public class SysRolePower {

    /**
     * 编号
     * */
    @TableId("id")
    private String id;

    /**
     * 角色编号
     * */
    @TableField("role_id")
    private String roleId;

    /**
     * 权限编号
     * */
    @TableField("power_id")
    private String powerId;
}
