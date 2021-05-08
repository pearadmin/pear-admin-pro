package com.pearadmin.pro.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.job.domain.SysJob;
import com.pearadmin.pro.modules.job.repository.SysJobRepository;
import com.pearadmin.pro.modules.job.param.SysJobRequest;
import com.pearadmin.pro.modules.job.service.SysJobService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 就眠仪式
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobRepository, SysJob> implements SysJobService {

    @Resource
    private SysJobRepository sysJobRepository;

    @Override
    public List<SysJob> list(SysJobRequest request) {
        return sysJobRepository.selectList(request);
    }

    @Override
    public PageResponse<SysJob> page(SysJobRequest request)
    {
        return Pageable.of(request,(()-> sysJobRepository.selectList(request)));
    }
}
