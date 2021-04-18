package com.pearadmin.pro.common.cache.impl;

import java.util.stream.Collectors;
import com.pearadmin.pro.common.cache.BaseCache;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.service.SysConfigService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 全局配置缓存
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/21
 * */
@Component
public class AllocationCache extends BaseCache<String> {

    @Resource
    private SysConfigService sysConfigService;

    /**
     * 配置信息
     */
    @Override
    public Map<String, String> load()
    {
        List<SysConfig> list = sysConfigService.list();
        return list.stream().collect(Collectors.toMap(e -> e.getKey(), SysConfig::getValue));
    }
}
