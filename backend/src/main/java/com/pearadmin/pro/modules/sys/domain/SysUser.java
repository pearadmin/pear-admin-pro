package com.pearadmin.pro.modules.sys.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.pearadmin.pro.common.secure.services.SecureUser;

/**
 * 用户领域模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Data
@Alias("SysUser")
@EqualsAndHashCode(callSuper = true)
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

    /**
     * 部门名称 (忽略)
     * */
    @TableField(exist = false)
    private String deptName;

    /**
     * 岗位
     * */
    @TableField("post_id")
    private String postId;

    /**
     * 岗位名称 (忽略)
     * */
    @TableField(exist = false)
    private String postName;

}
