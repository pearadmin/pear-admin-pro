package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.mapper.SysDeptMapper;
import com.pearadmin.pro.modules.sys.mapper.SysRoleMapper;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> dept(String roleId) {
        return sysDeptMapper.selectDeptByRoleId(roleId);
    }
}
