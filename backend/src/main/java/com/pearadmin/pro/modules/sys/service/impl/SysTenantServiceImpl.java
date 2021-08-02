package com.pearadmin.pro.modules.sys.service.impl;

import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.SysTenant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.sys.param.SysTenantRequest;
import com.pearadmin.pro.modules.sys.repository.SysTenantRepository;
import com.pearadmin.pro.modules.sys.service.SysTenantService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantRepository, SysTenant> implements SysTenantService {

    @Resource
    private SysTenantRepository sysTenantRepository;

    @Override
    public List<SysTenant> list(SysTenantRequest request) {
        return sysTenantRepository.selectTenant(request);
    }

    @Override
    public PageResponse<SysTenant> page(SysTenantRequest request) {
        return Pageable.of(request, (()-> sysTenantRepository.selectTenant(request)));
    }
}
