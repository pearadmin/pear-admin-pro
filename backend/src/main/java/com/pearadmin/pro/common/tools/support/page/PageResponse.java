package com.pearadmin.pro.common.tools.support.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 内存分页响应对象
 *
 * @author 就眠仪式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

    /**
     * 总页数
     * */
    private int total;

    /**
     * 当前页
     * */
    private int index;

    /**
     * 分页后的数据
     * */
    private T data;

}
