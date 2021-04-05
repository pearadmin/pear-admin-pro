package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.mapper.SysRoleMapper;
import com.pearadmin.pro.modules.sys.mapper.SysUserMapper;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysRole> role(String userId) {
        return sysRoleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<SysUser> list(SysUser sysUser) {
        return null;
    }

    @Override
    public List<SysUser> page(SysUser sysUser) {
        return null;
    }

    @Override
    public List<SysPower> menu(String userId) {
        return null;
    }
}
