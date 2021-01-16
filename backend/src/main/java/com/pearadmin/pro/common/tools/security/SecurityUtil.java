package com.pearadmin.pro.common.tools.security;

import com.pearadmin.pro.modules.sys.domain.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Describe: Security 工具类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class SecurityUtil {

     /**
      * Authentication 认证对象
      * */
     public static Authentication getAuthentication(){
          return SecurityContextHolder.getContext().getAuthentication();
     }

     /**
      * SysUser 当前用户
      * */
     public static SysUser getPrincipal(){ return (SysUser) getAuthentication().getPrincipal(); }

     /**
      * Username 当前用户名
      * */
     public static String getUsername(){ return getPrincipal().getUsername(); }

     /**
      * UserId 用户编号
      * */
     public static String getUserId(){ return getPrincipal().getId(); }
}
