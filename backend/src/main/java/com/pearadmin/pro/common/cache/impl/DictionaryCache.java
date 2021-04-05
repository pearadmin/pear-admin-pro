package com.pearadmin.pro.common.cache.impl;

import com.pearadmin.pro.common.cache.BaseCache;
import com.pearadmin.pro.modules.sys.domain.SysDictData;
import com.pearadmin.pro.modules.sys.domain.SysDictType;
import com.pearadmin.pro.modules.sys.mapper.SysDictDataMapper;
import com.pearadmin.pro.modules.sys.service.SysDictDataService;
import com.pearadmin.pro.modules.sys.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全 局 字 典 缓 存
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Component
public class DictionaryCache extends BaseCache<List<SysDictData>> {

    @Resource
    private SysDictDataService sysDictDataService;

    @Resource
    private SysDictTypeService sysDictTypeService;

    /**
     * 加载 字典 数据
     */
    @Override
    public Map<String, List<SysDictData>> load()
    {
        Map<String,List<SysDictData>> map = new HashMap<>();
        List<SysDictType> dictTypes = sysDictTypeService.list();

        dictTypes.forEach(dictType -> {
            map.put(dictType.getCode(),sysDictDataService.lambdaQuery().eq(SysDictData::getCode,dictType.getCode()).list());
        });
        return map;
    }
}
