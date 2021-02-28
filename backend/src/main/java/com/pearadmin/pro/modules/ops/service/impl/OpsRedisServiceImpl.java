package com.pearadmin.pro.modules.ops.service.impl;

import com.pearadmin.pro.modules.ops.service.OpsRedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OpsRedisServiceImpl implements OpsRedisService {

    @Resource
    private JedisPool jedisPool;

    @Override
    public String info() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Client client = jedis.getClient();
            client.info();
            String info = client.getBulkReply();
            return info;
        } finally {
            // 返还到连接池
            jedis.close();
        }
    }

    @Override
    public List<Slowlog> log(long count) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<Slowlog> logList = jedis.slowlogGet(count);
            return logList;
        } finally {
            jedis.close();
        }
    }

}
