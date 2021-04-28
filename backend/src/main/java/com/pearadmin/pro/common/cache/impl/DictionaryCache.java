package com.pearadmin.pro.common.cache.impl;

import com.pearadmin.pro.common.cache.BaseCache;
import com.pearadmin.pro.modules.sys.domain.SysDictData;
import com.pearadmin.pro.modules.sys.domain.SysDict;
import com.pearadmin.pro.modules.sys.param.SysDictDataRequest;
import com.pearadmin.pro.modules.sys.service.SysDictDataService;
import com.pearadmin.pro.modules.sys.service.SysDictService;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局字典缓存
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/21
 * */
@Component
public class DictionaryCache extends BaseCache<List<SysDictData>> {

    @Resource
    private SysDictDataService sysDictDataService;

    @Resource
    private SysDictService sysDictService;

    @Override
    public Map<String, List<SysDictData>> load()
    {
        Map<String,List<SysDictData>> map = new HashMap<>();
        List<SysDict> dictTypes = sysDictService.list();
        dictTypes.forEach(dictType -> {
            map.put(dictType.getCode(),sysDictDataService.list(new SysDictDataRequest()));
        });
        return map;
    }
}
