package com.pearadmin.pro.common.tools.string;

import com.pearadmin.pro.common.constant.SystemConstant;

/**
 * Describe: String 工具类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class StringUtil {

    public boolean isBlank(String text){
        if(text.equals(SystemConstant.EMPTY)){ return true; }
        return false;
    }

    public boolean isEmpty(String text){
        if(text == null){ return true; }
        return false;
    }

    public boolean isNotBlank(String text){
        return !isBlank(text);
    }

    public boolean isNotEmpty(String text){
        return !isNotEmpty(text);
    }
}
