package com.pearadmin.pro.common.secure.services;

import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import com.pearadmin.pro.modules.sys.service.SysPowerService;
import com.pearadmin.pro.modules.sys.service.SysUserService;
import com.pearadmin.pro.modules.sys.service.impl.SysUserServiceImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * Security 用户服务实现
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/23
 * */
@Component
public class SecureUserService implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    /**
     * 加载用户信息
     * */
    @Override
    public SecureUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SecureUser customUserDetails = sysUserService.lambdaQuery().eq(SysUser::getUsername,username).one();
        if(customUserDetails==null) {
            throw new UsernameNotFoundException("USERNAME NOT SUPPORT");
        }

        // TODO 载入权限
        List<SysPower> powers = sysUserService.power(customUserDetails.getId());

        return customUserDetails;
    }

}
