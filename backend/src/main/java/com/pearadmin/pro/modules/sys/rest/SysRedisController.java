package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.aop.annotation.SysLog;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * 缓存控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Api(tags = {"缓存"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "redis")
public class SysRedisController extends BaseController {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * Redis 服务信息
     */
    @GetMapping("info")
    @SysLog(title = "缓存详情")
    @ApiOperation(value = "缓存详情")
    public Result info(){
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());
        Map<String, Object> result = new HashMap<>(2);
        result.put("info", info);
        result.put("size", dbSize);
        return success(result);
    }

}
