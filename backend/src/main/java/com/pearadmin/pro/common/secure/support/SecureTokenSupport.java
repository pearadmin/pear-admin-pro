package com.pearadmin.pro.common.secure.support;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pearadmin.pro.common.secure.services.SecureUser;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.common.tools.core.spring.SpringUtil;
import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.common.web.domain.ResultCode;
import com.pearadmin.pro.common.constant.TokenConstant;
import com.pearadmin.pro.common.constant.SecurityConstant;
import com.pearadmin.pro.common.web.exception.auth.token.TokenExpiredException;
import com.pearadmin.pro.common.secure.services.SecureUserTokenService;
import com.pearadmin.pro.common.web.exception.auth.token.TokenValidationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Describe: Token Filter 主要增加 Token 的验证
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class SecureTokenSupport extends OncePerRequestFilter {

    private SecureUserTokenService customUserDetailsTokenService;

    public SecureTokenSupport(){
        this.customUserDetailsTokenService = SpringUtil.getBean("secureUserTokenService", SecureUserTokenService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(TokenConstant.TOKEN_HEADER);
        // token param verify empty
        if (tokenHeader == null) { ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_MISSION)); return;}
        // token verify
        SecureUser customUserDetails;
        try {
            customUserDetails = customUserDetailsTokenService.verifyToken(tokenHeader.replaceFirst(TokenConstant.TOKEN_PREFIX, ""));
        }catch (TokenValidationException e){
            ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_INVALID)); return;
        }catch (TokenExpiredException e){
            ServletUtil.writeJson(Result.failure(ResultCode.TOKEN_EXPIRED)); return;
        }
        // return UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customUserDetails, customUserDetails.getId(), customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        List<String> urls = Arrays.asList(SecurityConstant.HTTP_ACT_MATCHERS.split(","));
        boolean b = false;
        for (String url: urls) {
            if(request.getRequestURI().equals(url)) b = true;
        }
        return b;
    }
}
