package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.domain.SysUser;

import java.util.List;


public interface SysUserService extends IService<SysUser> {

    public List<SysRole> role(String userId);
}
