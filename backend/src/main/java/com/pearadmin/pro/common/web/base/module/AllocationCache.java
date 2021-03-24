package com.pearadmin.pro.common.web.base.module;

import com.pearadmin.pro.common.web.base.BaseCache;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.service.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Describe: 全局配置缓存
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Slf4j
@Component
public class AllocationCache extends BaseCache<String> {

    @Resource
    private SysConfigService sysConfigService;

    @Override
    public Map<String, String> load()
    {
        List<SysConfig> list = sysConfigService.list();

        // TO MAP
        Map<String, String> map = list.stream().collect(Collectors.toMap(e -> e.getKey(), SysConfig::getValue));

        // LOG
        log.info("刷新全局配置, 数量：" + map.size());

        // RETURN
        return map;
    }
}
