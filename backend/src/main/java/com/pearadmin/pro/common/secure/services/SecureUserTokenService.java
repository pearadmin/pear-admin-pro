package com.pearadmin.pro.common.secure.services;

import java.util.*;
import io.jsonwebtoken.Jwts;
import java.util.concurrent.TimeUnit;
import io.jsonwebtoken.SignatureAlgorithm;
import com.pearadmin.pro.common.constant.CacheNameConstant;
import com.pearadmin.pro.common.constant.SecurityConstant;
import com.pearadmin.pro.common.constant.TokenConstant;
import com.pearadmin.pro.common.web.exception.auth.token.TokenException;
import com.pearadmin.pro.common.web.exception.auth.token.TokenExpiredException;
import com.pearadmin.pro.common.web.exception.auth.token.TokenValidationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * Describe: Security User Token 操作服务
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureUserTokenService {

    @Resource
    private RedisTemplate<String, SecureUser> redisTemplate;

    /**
     * 创建 Token
     * */
    public String createToken(SecureUser customUserDetails){
        return Jwts.builder()
                .setId(customUserDetails.getId())
                .setSubject(customUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setIssuer(TokenConstant.ISSUER)
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET)
                .compact();
    }

    /**
     * 存储 Token
     * */
    public void saveToken(String token, SecureUser customUserDetails){
        redisTemplate.opsForValue().set(CacheNameConstant.TOKEN_NAME_PREFIX + token, customUserDetails,  SecurityConstant.TOKEN_EXPIRATION, TimeUnit.SECONDS);
    }

    /**
     * 创建 并 存储 Token
     * */
    public String createAndSaveToken(SecureUser customUserDetails){
        String token = createToken(customUserDetails);
        saveToken(token,customUserDetails);
        return token;
    }

    /**
     * 验证 Token
     * */
    public SecureUser verifyToken(String token) throws TokenException{
        parseToken(token);
        SecureUser customUserDetails = taskToken(token);
        if(customUserDetails == null) throw new TokenExpiredException("token expired");
        return customUserDetails;
    }

    /**
     * 解析 Token
     * */
    public void parseToken(String token) {
        try {
            Jwts.parser().setSigningKey(TokenConstant.SECRET).parseClaimsJws(token).getBody();
        }catch (Exception e){
            throw new TokenValidationException("token validation failure");
        }
    }

    /**
     * 获取 Token
     * */
    public SecureUser taskToken(String token){
        return redisTemplate.opsForValue().get(CacheNameConstant.TOKEN_NAME_PREFIX + token);
    }

    /**
     * 销毁 Token
     * */
    public void destroyToken(String token){
        redisTemplate.delete(CacheNameConstant.TOKEN_NAME_PREFIX + token);
    }

    /**
     * 查询 过期 (秒)
     * 如果该值有过期时间，就返回相应的过期时间;
     * 如果该值没有设置过期时间，就返回-1;
     * 如果没有该值，就返回-2;
     * */
    public long selectExpired(String key){
        long expired = redisTemplate.opsForValue().getOperations().getExpire(key);
        return expired;
    }

    /**
     * 查询 Token
     * */
    public Set<String> selectToken(){
        Set<String> tokenKeys= redisTemplate.keys(CacheNameConstant.TOKEN_NAME_PREFIX + "*");
        return tokenKeys;
    }
}
