package com.pearadmin.pro.generate.config;

import org.springframework.stereotype.Component;

/**
 * 代码生成配置
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Component
public class GenConfig {

    /**
     * 作者
     **/
    public static String author = "jmys";

    /**
     * 生成包路径
     **/
    public static String packageName = "com.pearadmin.modules";

    /**
     * 自动去除表前缀，默认是false
     **/
    public static boolean autoRemovePre = false;

    /**
     * 表前缀(类名不会包含表前缀)
     **/
    public static String tablePrefix = "sys_";

    public static String getAuthor() {
        return author;
    }

    public static void setAuthor(String author) {
        GenConfig.author = author;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        GenConfig.packageName = packageName;
    }

    public static boolean getAutoRemovePre() {
        return autoRemovePre;
    }

    public static void setAutoRemovePre(boolean autoRemovePre) {
        GenConfig.autoRemovePre = autoRemovePre;
    }

    public static String getTablePrefix() {
        return tablePrefix;
    }

    public static void setTablePrefix(String tablePrefix) {
        GenConfig.tablePrefix = tablePrefix;
    }

}