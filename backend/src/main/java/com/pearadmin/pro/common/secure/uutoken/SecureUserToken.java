package com.pearadmin.pro.common.secure.uutoken;

import com.pearadmin.pro.common.secure.services.SecureUser;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户 Token 封装实体
 * */
@Data
public class SecureUserToken {

    /**
     * Token
     * */
    private String token;

    /**
     * 基本信息
     * */
    private SecureUser secureUser;

    /**
     * 创建时间
     * */
    private LocalDateTime createTime = LocalDateTime.now();
}
