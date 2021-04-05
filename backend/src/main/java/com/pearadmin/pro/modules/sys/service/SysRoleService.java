package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取角色部门
     *
     * @param roleId 用户编号
     * */
    List<SysDept> dept(String roleId);

}
