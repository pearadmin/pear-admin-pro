package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.*;
import com.pearadmin.pro.modules.sys.repository.SysDeptRepository;
import com.pearadmin.pro.modules.sys.repository.SysPowerRepository;
import com.pearadmin.pro.modules.sys.repository.SysRolePowerRepository;
import com.pearadmin.pro.modules.sys.repository.SysRoleRepository;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;
import com.pearadmin.pro.modules.sys.service.SysRolePowerService;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import com.pearadmin.pro.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleRepository, SysRole> implements SysRoleService {

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRolePowerService sysRolePowerService;

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysDept> dept(String roleId) {
        return sysDeptRepository.selectDeptByRoleId(roleId);
    }

    @Override
    public List<SysRole> list(SysRoleRequest request) {
        return sysRoleRepository.selectList(request);
    }

    @Override
    public PageResponse<SysRole> page(SysRoleRequest request) {
        return Pageable.of(request,() -> sysRoleRepository.selectList(request));
    }

    @Override
    @Transactional
    public Boolean give(String roleId, List<String> powerIds) {
        sysRolePowerService.lambdaUpdate().eq(SysRolePower::getRoleId, roleId).remove();
        List<SysRolePower> rolePowers = new ArrayList<>();
        powerIds.forEach(powerId -> {
            SysRolePower rolePower = new SysRolePower();
            rolePower.setRoleId(roleId);
            rolePower.setPowerId(powerId);
            rolePowers.add(rolePower);
        });
        return sysRolePowerService.saveBatch(rolePowers);
    }

    @Override
    public List<SysPower> power(String roleId) {
        return sysPowerRepository.selectPowerByRoleId(roleId);
    }

    @Override
    @Transactional
    public boolean removeById(Serializable id) {
        sysRoleRepository.deleteById(id);
        sysRolePowerService.lambdaUpdate().eq(SysRolePower::getRoleId,id).remove();
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getRoleId,id).remove();
        return true;
    }

    @Override
    @Transactional
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.forEach(id -> {
            removeById(id);
        });
        return true;
    }
}
