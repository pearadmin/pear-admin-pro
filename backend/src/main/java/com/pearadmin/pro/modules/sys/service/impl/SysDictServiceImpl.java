package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.SysDict;
import com.pearadmin.pro.modules.sys.repository.SysDictRepository;
import com.pearadmin.pro.modules.sys.param.SysDictRequest;
import com.pearadmin.pro.modules.sys.service.SysDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictRepository, SysDict> implements SysDictService {

    @Resource
    private SysDictRepository sysDictRepository;

    @Override
    public List<SysDict> list(SysDictRequest request) {
        return sysDictRepository.selectList(request);
    }

    @Override
    public PageResponse<SysDict> page(SysDictRequest request) {
        return Pageable.of(request,(()-> sysDictRepository.selectList(request)));
    }
}
