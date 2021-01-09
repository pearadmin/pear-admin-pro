package com.pearadmin.pro.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pearadmin.pro.modules.log.mapper.LogMapper;
import com.pearadmin.pro.modules.log.service.LogService;
import org.springframework.stereotype.Service;
import com.pearadmin.pro.modules.log.domain.Log;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper,Log> implements LogService {

}
