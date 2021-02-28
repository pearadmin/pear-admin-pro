package com.pearadmin.pro.modules.ops.controller;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.modules.ops.service.OpsRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import redis.clients.util.Slowlog;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(ControllerConstant.PREFIX_OPS + "redis")
public class OpsRedisController extends BaseController {

    @Resource
    private OpsRedisService opsRedisService;

    @GetMapping("info")
    public Result info(){
        return success(opsRedisService.info());
    }

    @GetMapping("log")
    public Result log(){
        List<Slowlog> list = opsRedisService.log(20);
        return success(list);
    }
}
