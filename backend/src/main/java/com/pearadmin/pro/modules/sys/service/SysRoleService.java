package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.tools.support.server.server.Sys;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    public List<SysDept> dept(String roleId);
}
