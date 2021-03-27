package com.pearadmin.pro.common.web.base.context;

import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * User Context
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class UserContext {

    /**
     * User Service 用户服务
     * */
    @Resource
    private SysUserService sysUserService;

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
     * Role 当前用户角色集合
     * */
    public List<SysRole> getRole(){ return sysUserService.role(getUserId()); }

    /**
     * deptId 当前用户所属部门
     * */
    public String getDeptId(){ return getPrincipal().getDeptId(); }

}
