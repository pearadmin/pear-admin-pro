package com.pearadmin.pro.common.web.context;

import com.pearadmin.pro.common.web.domain.ResultController;

/**
 * Describe: Base Context
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public abstract class BaseContext extends ResultController {

    public static UserContext USER_CONTEXT;
    public static AuthContext AUTH_CONTEXT;

    static{
        USER_CONTEXT = new UserContext();
        AUTH_CONTEXT = new AuthContext();
    }
}
