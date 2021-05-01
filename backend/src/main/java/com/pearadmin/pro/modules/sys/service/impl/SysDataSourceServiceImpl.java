package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.SysDataSource;
import com.pearadmin.pro.modules.sys.param.SysDataSourceRequest;
import com.pearadmin.pro.modules.sys.repository.SysDataSourceRepository;
import com.pearadmin.pro.modules.sys.service.SysDataSourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDataSourceServiceImpl extends ServiceImpl<SysDataSourceRepository, SysDataSource>
implements SysDataSourceService {

    @Resource
    private SysDataSourceRepository sysDataSourceRepository;

    @Override
    public List<SysDataSource> list(SysDataSourceRequest request) {
        return sysDataSourceRepository.selectList(request);
    }

    @Override
    public PageResponse<SysDataSource> page(SysDataSourceRequest request) {
        return Pageable.of(request, () -> sysDataSourceRepository.selectList(request));
    }
}
