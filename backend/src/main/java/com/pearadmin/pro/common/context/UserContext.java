package com.pearadmin.pro.common.context;

import com.pearadmin.pro.modules.sys.domain.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * User Context
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
@Component
public class UserContext {

    /**
     * Authentication 认证对象
     * */
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * SysUser 当前用户
     * */
    public SysUser getPrincipal(){ return (SysUser) getAuthentication().getPrincipal(); }

    /**
     * Username 当前用户名
     * */
    public String getUsername(){ return getPrincipal().getUsername(); }

    /**
     * nickname 当前用户昵称
     * */
    public String getNickName(){ return getPrincipal().getNickname(); }

    /**
     * UserId 用户编号
     * */
    public String getUserId(){ return getPrincipal().getId(); }

    /**
     * deptId 当前用户所属部门
     * */
    public String getDeptId(){ return getPrincipal().getDeptId(); }

}
