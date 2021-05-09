package com.pearadmin.pro.common.quartz.impl;

import com.pearadmin.pro.common.quartz.base.BaseQuartz;
import org.springframework.stereotype.Component;

/**
 * Http Quartz 请求任务
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Component("http")
public class SimpleHttpQuartz implements BaseQuartz {

    /**
     * @param param Http 信息
     * */
    @Override
    public void run(String param) {
        // TODO Simple Http

    }
}
