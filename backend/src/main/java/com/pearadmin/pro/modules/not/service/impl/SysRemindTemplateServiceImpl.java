package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.not.domain.SysRemindTemplate;
import com.pearadmin.pro.modules.not.repository.SysRemindTemplateRepository;
import com.pearadmin.pro.modules.not.service.SysRemindTemplateService;
import org.springframework.stereotype.Service;

@Service
public class SysRemindTemplateServiceImpl extends ServiceImpl<SysRemindTemplateRepository, SysRemindTemplate>
implements SysRemindTemplateService {
}
