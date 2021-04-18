package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.modules.sys.domain.SysDept;
import com.pearadmin.pro.modules.sys.domain.SysPower;

import java.util.List;

public interface SysPowerService extends IService<SysPower> {

    /**
     * 获取权限树
     * */
    List<SysPower> tree();
}
