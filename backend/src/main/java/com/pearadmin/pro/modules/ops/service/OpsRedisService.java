package com.pearadmin.pro.modules.ops.service;

import redis.clients.util.Slowlog;

import java.util.List;

public interface OpsRedisService {

    /**
     * 详细信息
     * */
    public String info();

    /**
     * 运行日志
     * */
    public List<Slowlog> log(long count);

}
