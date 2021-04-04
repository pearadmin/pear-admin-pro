package com.pearadmin.pro.common.cache.impl;

import com.pearadmin.pro.common.cache.BaseCache;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * 全 局 字 典 缓 存
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */

@Component
public class DictionaryCache extends BaseCache<String> {

    @Override
    public Map<String, String> load() {
        return null;
    }
}
