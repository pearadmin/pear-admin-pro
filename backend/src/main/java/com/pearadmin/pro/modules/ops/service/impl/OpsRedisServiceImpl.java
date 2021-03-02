package com.pearadmin.pro.modules.ops.service.impl;

import com.pearadmin.pro.modules.ops.service.OpsRedisService;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;
import javax.annotation.Resource;
import java.util.List;
@Service
public class OpsRedisServiceImpl implements OpsRedisService {

    @Resource
    private JedisPool jedisPool;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public String info() {
       return null;
    }

    @Override
    public List<Slowlog> log(long count) {
        return null;
    }

}
