package com.pearadmin.pro.common.context;

import org.quartz.Scheduler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Task Context
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/10/23
 * */
@Component
public class TaskContext {

    @Resource
    private Scheduler scheduler;

    /**
     * 保存定时任务
     * */
    public Boolean add(){

        return true;
    }

    /**
     * 恢复定时任务
     * */
    public Boolean resume(){

        return true;
    }

    /**
     * 修改定时任务
     * */
    public Boolean update(){

        return true;
    }

    /**
     * 停止定时任务
     * */
    public Boolean pause(){

        return true;
    }

    /**
     * 删除定时任务
     * */
    public Boolean delete(){

        return true;
    }

    /**
     * 是否存在
     * */
    public Boolean exists(){

        return true;
    }
}
