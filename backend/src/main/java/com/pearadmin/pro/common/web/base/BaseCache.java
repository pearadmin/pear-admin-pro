package com.pearadmin.pro.common.web.base;

import org.springframework.scheduling.annotation.Scheduled;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean Cache
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
public abstract class BaseCache<T> {

    /**
     * 容器
     * */
    public Map<String,T> cache = new HashMap<>();

    /**
     * 获取
     * */
    public T get(String key){

        // GET API
        return cache.get(key);
    }

    /**
     * 刷新
     * */
    public void refresh(){

        // REFRESH
        cache = load();
    }

    /**
     * 加载
     * */
    public abstract Map<String,T> load();

    /**
     * 刷新
     * */
    @Scheduled(fixedDelay = 5000)
    public void time(){

        // LOAD
        cache = load();
    }
}
