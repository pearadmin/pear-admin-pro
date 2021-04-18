package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.domain.SysRole;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.repository.SysPowerRepository;
import com.pearadmin.pro.modules.sys.repository.SysRoleRepository;
import com.pearadmin.pro.modules.sys.repository.SysUserRepository;
import com.pearadmin.pro.modules.sys.param.SysUserRequest;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserRepository, SysUser> implements SysUserService {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Override
    public List<SysRole> role(String userId) {
        return sysRoleRepository.selectRoleByUserId(userId);
    }

    @Override
    public List<SysPower> menu(String userId) {
        return toTree(sysPowerRepository.selectMenu(userId),"0");
    }

    @Override
    public List<SysUser> list(SysUserRequest request) {
        return sysUserRepository.selectList(request);
    }

    @Override
    public PageResponse<SysUser> page(SysUserRequest request) {
        return Pageable.of(request, (()-> sysUserRepository.selectList(request)));
    }

    @Override
    public List<SysPower> power(String userId) {
        return sysPowerRepository.selectListByUserId(userId);
    }

    /**
     * Describe: 递归获取菜单tree
     * Param: sysMenus
     * Return: 操作结果
     * */
    public List<SysPower> toTree(List<SysPower> sysMenus,String parent) {
        List<SysPower> list = new ArrayList<>();
        for (SysPower menu : sysMenus) {
            if (parent.equals(menu.getParent())) {
                menu.setChildren(toTree(sysMenus, menu.getId()));
                list.add(menu);
            }
        }
        return list;
    }

}
