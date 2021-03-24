package com.pearadmin.pro.common.web.base.module;

import com.pearadmin.pro.common.web.base.BaseCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * Describe: 全局字典缓存
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Slf4j
@Component
public class DictionaryCache extends BaseCache<String> {

    @Override
    public Map<String, String> load() {
        return null;
    }
}
