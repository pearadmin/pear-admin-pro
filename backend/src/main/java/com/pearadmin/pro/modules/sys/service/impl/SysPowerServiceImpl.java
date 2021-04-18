package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.sys.domain.SysPower;
import com.pearadmin.pro.modules.sys.repository.SysPowerRepository;
import com.pearadmin.pro.modules.sys.service.SysPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPowerServiceImpl extends ServiceImpl<SysPowerRepository, SysPower> implements SysPowerService {

    @Resource
    private SysPowerRepository sysPowerRepository;

    @Override
    public List<SysPower> tree() {
        return sysPowerRepository.selectTree();
    }
}
