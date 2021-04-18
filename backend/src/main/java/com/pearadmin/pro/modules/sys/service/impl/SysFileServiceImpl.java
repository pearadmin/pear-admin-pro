package com.pearadmin.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.common.web.base.page.Pageable;
import com.pearadmin.pro.modules.sys.domain.SysFile;
import com.pearadmin.pro.modules.sys.repository.SysFileRepository;
import com.pearadmin.pro.modules.sys.param.SysFileRequest;
import com.pearadmin.pro.modules.sys.service.SysFileService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileRepository, SysFile> implements SysFileService {

    @Resource
    private SysFileRepository sysFileRepository;

    @Override
    public List<SysFile> list(SysFileRequest request) {
        return sysFileRepository.selectList(request);
    }

    @Override
    public PageResponse<SysFile> page(SysFileRequest request) {
        return Pageable.of(request,(()-> sysFileRepository.selectList(request)));
    }
}
