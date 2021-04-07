package com.pearadmin.pro.common.web.base.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页参数
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    /**
     * 当前页
     * */
    private int pageNum;

    /**
     * 页数量
     * */
    private int pageSize;
}
