package com.pearadmin.pro.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Describe: Bean Context
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class BeanContext  implements ApplicationContextAware {

    /**
     * Spring Ioc 上下文
     * */
    public static ApplicationContext applicationContext;

    /**
     * 设置 Spring 上下文
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取 Bean 实体
     * */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 获取 Bean 实例
     * */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * Bean 是否存在
     * */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * Bean 是否为单例
     * */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取 Bean 类型
     * */
    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }

}
