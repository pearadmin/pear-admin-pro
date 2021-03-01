package com.pearadmin.pro.common.context;

import org.springframework.stereotype.Component;

/**
 * Describe: Base Context
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public abstract class BaseContext {

    public static UserContext USER_CONTEXT;

    static{
        USER_CONTEXT = new UserContext();
    }

}
