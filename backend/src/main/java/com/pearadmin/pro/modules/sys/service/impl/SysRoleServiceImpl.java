package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.repository.SysDeptRepository;
import com.pearadmin.pro.modules.sys.repository.SysRoleRepository;
import com.pearadmin.pro.modules.sys.param.SysRoleRequest;
import com.pearadmin.pro.modules.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleRepository, SysRole> implements SysRoleService {

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Resource
    private SysRoleRepository sysRoleRepository;

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
}
