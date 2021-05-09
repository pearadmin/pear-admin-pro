package com.pearadmin.pro.common.quartz.impl;

import com.pearadmin.pro.common.quartz.base.BaseQuartz;
import org.springframework.stereotype.Component;

/**
 * Shell Quartz 脚本任务
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Component("shell")
public class SimpleShellQuartz implements BaseQuartz {

    /**
     * @param param Shell 脚本
     * */
    @Override
    public void run(String param) {
        // TODO Simple Shell

    }
}
