package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.pearadmin.pro.common.constant.TenantConstant;
import com.pearadmin.pro.common.tools.support.server.server.Sys;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.sys.param.SysTenantRequest;
import com.pearadmin.pro.modules.sys.param.SysTenantSaveRequest;
import com.pearadmin.pro.modules.sys.repository.*;
import com.pearadmin.pro.modules.sys.service.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantRepository, SysTenant> implements SysTenantService {

    @Resource
    private SysTenantRepository sysTenantRepository;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysTenantPowerService sysTenantPowerService;

    @Resource
    private SysRolePowerService sysRolePowerService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysDeptService sysDeptService;

    @Resource
    private SysPostService sysPostService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public List<SysTenant> list(SysTenantRequest request) {
        return sysTenantRepository.selectTenant(request);
    }

    @Override
    public PageResponse<SysTenant> page(SysTenantRequest request) {
        return Pageable.of(request, (()-> sysTenantRepository.selectTenant(request)));
    }

    @Override
    @Transactional
    public Boolean save(SysTenantSaveRequest request) {

        String userId = IdWorker.getIdStr();
        String tenantId = IdWorker.getIdStr();
        String roleId = IdWorker.getIdStr();
        String deptId = IdWorker.getIdStr();
        String postId = IdWorker.getIdStr();
        SysTenant tenant = request.getTenant();
        sysTenantRepository.insert(tenant);

        SysPost sysPost = new SysPost();
        sysPost.setId(postId);
        sysPost.setName("默认岗位");
        sysPost.setSort(0);
        sysPost.setEnable(true);
        sysPost.setCode("default");
        sysPost.setTenantId(tenantId);
        sysPostService.save(sysPost);

        SysDept dept = new SysDept();
        dept.setSort(0);
        dept.setEnable(true);
        dept.setId(deptId);
        dept.setParent("0");
        dept.setName("顶级部门");
        dept.setAddress("详细地址");
        dept.setTenantId(tenantId);
        sysDeptService.save(dept);

        SysUser user = request.getUser();
        user.setId(userId);
        user.setEnable(true);
        user.setLocked(false);
        user.setDeptId(deptId);
        user.setTenantId(tenantId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sysUserService.save(user);

        SysRole role = new SysRole();
        role.setId(roleId);
        role.setEnable(true);
        role.setTenantId(tenantId);
        role.setName(TenantConstant.DEFAULT_ROLE_NAME);
        role.setCode(TenantConstant.DEFAULT_ROLE_CODE);
        sysRoleService.save(role);

        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        userRole.setTenantId(tenantId);
        sysUserRoleService.save(userRole);

        List<String> powerIds = request.getPowerIds();

        for (String powerId: powerIds) {
            SysTenantPower tenantPower = new SysTenantPower();
            tenantPower.setTenantId(tenantId);
            tenantPower.setPowerId(powerId);

            SysRolePower rolePower = new SysRolePower();
            rolePower.setRoleId(roleId);
            rolePower.setPowerId(powerId);
            rolePower.setTenantId(tenantId);

            sysRolePowerService.save(rolePower);
            sysTenantPowerService.save(tenantPower);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean removeById(Serializable id) {
        sysTenantRepository.deleteById(id);
        sysTenantPowerService.lambdaUpdate().eq(SysTenantPower::getTenantId, id).remove();
        sysUserService.lambdaUpdate().eq(SysUser::getTenantId, id).remove();
        sysUserRoleService.lambdaUpdate().eq(SysUserRole::getTenantId, id).remove();
        sysRoleService.lambdaUpdate().eq(SysRole::getTenantId, id).remove();
        sysRolePowerService.lambdaUpdate().eq(SysRolePower::getTenantId, id).remove();
        sysDeptService.lambdaUpdate().eq(SysDept::getTenantId, id).remove();
        sysPostService.lambdaUpdate().eq(SysPost::getTenantId, id).remove();
        return true;
    }
}
