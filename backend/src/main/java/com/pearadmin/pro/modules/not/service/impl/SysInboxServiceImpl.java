package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.not.domain.SysInbox;
import com.pearadmin.pro.modules.not.repository.SysInboxRepository;
import com.pearadmin.pro.modules.not.service.SysInboxService;
import org.springframework.stereotype.Service;

@Service
public class SysInboxServiceImpl extends ServiceImpl<SysInboxRepository, SysInbox> implements SysInboxService {
}
