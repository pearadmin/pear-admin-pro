package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.not.domain.SysRemindSubscribe;
import com.pearadmin.pro.modules.not.param.SysRemindSubscribeRequest;
import com.pearadmin.pro.modules.not.repository.SysRemindSubscribeRepository;
import com.pearadmin.pro.modules.not.service.SysRemindSubscribeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRemindSubscribeServiceImpl extends ServiceImpl<SysRemindSubscribeRepository, SysRemindSubscribe>
implements SysRemindSubscribeService {

    @Resource
    private SysRemindSubscribeRepository sysRemindSubscribeRepository;

    @Override
    public List<SysRemindSubscribe> list(SysRemindSubscribeRequest request) {
        return sysRemindSubscribeRepository.selectRemindSubscribe(request);
    }

    @Override
    public PageResponse<SysRemindSubscribe> page(SysRemindSubscribeRequest request) {
        return  Pageable.of(request, (()-> sysRemindSubscribeRepository.selectRemindSubscribe(request)));
    }
}
