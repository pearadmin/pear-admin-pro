package com.pearadmin.pro.modules.sys.controller;

import com.pearadmin.pro.common.constant.CacheNameConstant;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.secure.uutoken.SecureUserToken;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Describe: 在线用户控制器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "online")
public class SysOnlineController extends BaseController {

    @Resource
    private RedisTemplate<String, SecureUserToken> redisTemplate;

    @GetMapping("list")
    public Result list(){
        Set<String> keys = redisTemplate.keys(CacheNameConstant.TOKEN_NAME_PREFIX + "*");
        Set<SecureUserToken> online = new HashSet<>();
        keys.forEach(key -> {
            SecureUserToken userToken = redisTemplate.opsForValue().get(key);
            online.add(userToken);
        });
        return success(online);
    }
}
