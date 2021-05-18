package com.pearadmin.pro.modules.not.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.not.domain.SysRemind;
import com.pearadmin.pro.modules.not.param.SysRemindRequest;
import com.pearadmin.pro.modules.not.repository.SysRemindRepository;
import com.pearadmin.pro.modules.not.service.SysRemindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRemindServiceImpl extends ServiceImpl<SysRemindRepository, SysRemind> implements SysRemindService {

    @Resource
    private SysRemindRepository sysRemindRepository;

    @Override
    public List<SysRemind> list(SysRemindRequest request) {
        return sysRemindRepository.selectRemind(request);
    }

    @Override
    public PageResponse<SysRemind> page(SysRemindRequest request) {
        return Pageable.of(request, (()-> sysRemindRepository.selectRemind(request)));
    }
}
