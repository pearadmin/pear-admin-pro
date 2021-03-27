package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.secure.services.SecureUser;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Describe: 用户领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
@Alias("SysUser")
@TableName(value="sys_user")
public class SysUser extends SecureUser {

    /**
     * 昵称
     * */
    @TableField("nickname")
    private String nickname;

    /**
     * 邮箱
     * */
    @TableField("email")
    private String email;

    /**
     * 电话
     * */
    @TableField("phone")
    private String phone;

    /**
     * 头像
     * */
    @TableField("avatar")
    private String avatar;

    /**
     * 性别
     * */
    @TableField("gender")
    private Integer gender;

    /**
     * 部门
     * */
    @TableField("dept_id")
    private String deptId;

}
