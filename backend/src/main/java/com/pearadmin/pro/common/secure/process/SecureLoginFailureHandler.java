package com.pearadmin.pro.common.secure.process;

import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.common.web.domain.ResultCode;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Describe: Security 登陆失败处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {

        if(e instanceof UsernameNotFoundException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_USERNAME_NOT_FOUND)); return;}
        if(e instanceof LockedException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_LOCKED)); return;}
        if(e instanceof BadCredentialsException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_BAD_CREDENTIALS)); return;}
        if(e instanceof AccountExpiredException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_EXPIRED)); return;}
        if(e instanceof DisabledException){ ServletUtil.writeJson(Result.failure(ResultCode.USER_NOT_ENABLE)); return;}

        ServletUtil.writeJson(Result.failure(ResultCode.LOGIN_FAILURE));
    }
}
