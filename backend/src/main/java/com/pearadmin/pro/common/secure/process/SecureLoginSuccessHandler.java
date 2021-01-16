package com.pearadmin.pro.common.secure.process;

import com.pearadmin.pro.common.web.domain.Result;
import com.pearadmin.pro.common.web.domain.ResultCode;
import com.pearadmin.pro.common.constant.TokenConstant;
import org.springframework.security.core.Authentication;
import com.pearadmin.pro.common.secure.services.SecureUserTokenService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.pearadmin.pro.common.secure.services.SecureUser;
import com.pearadmin.pro.common.tools.servlet.ServletUtil;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Describe: Security 登陆成功处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private SecureUserTokenService customUserDetailsTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
            SecureUser customUserDetails = (SecureUser) authentication.getPrincipal();
            String accessToken = TokenConstant.TOKEN_PREFIX + customUserDetailsTokenService.createAndSaveToken(customUserDetails);;
            Result result = Result.success(ResultCode.LOGIN_SUCCESS).setToken(accessToken);;
            ServletUtil.writeJson(result);
    }
}
