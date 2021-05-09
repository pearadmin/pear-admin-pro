package com.pearadmin.pro.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.job.domain.SysJobLog;
import com.pearadmin.pro.modules.job.repository.SysJobLogRepository;
import com.pearadmin.pro.modules.job.param.SysJobLogRequest;
import com.pearadmin.pro.modules.job.service.SysJobLogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 就眠仪式
 */
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogRepository, SysJobLog> implements SysJobLogService {

    @Resource
    private SysJobLogRepository sysJobLogRepository;

    @Override
    public List<SysJobLog> list(SysJobLogRequest request) {
        return sysJobLogRepository.selectJobLog(request);
    }

    @Override
    public PageResponse<SysJobLog> page(SysJobLogRequest request) {
        return Pageable.of(request,(()-> sysJobLogRepository.selectJobLog(request)));
    }
}
