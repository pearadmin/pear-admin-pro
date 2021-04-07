package com.pearadmin.pro.common.web.base.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Base Page 分页实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Data
public class PageDomain implements Serializable {

    /**
     * 当前页码
     */
    private int limit = 1;

    /**
     * 每页数量
     * */
    private int size = 10;

}
