package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.not.domain.SysRemindTemplate;
import com.pearadmin.pro.modules.not.param.SysRemindTemplateRequest;
import com.pearadmin.pro.modules.not.repository.SysRemindTemplateRepository;
import com.pearadmin.pro.modules.not.service.SysRemindTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRemindTemplateServiceImpl extends ServiceImpl<SysRemindTemplateRepository, SysRemindTemplate>
implements SysRemindTemplateService {

    @Resource
    private SysRemindTemplateRepository sysRemindTemplateRepository;

    @Override
    public List<SysRemindTemplate> list(SysRemindTemplateRequest request) {
        return sysRemindTemplateRepository.selectRemindTemplate(request);
    }

    @Override
    public PageResponse<SysRemindTemplate> page(SysRemindTemplateRequest request) {
        return Pageable.of(request, (()-> sysRemindTemplateRepository.selectRemindTemplate(request)));
    }
}
