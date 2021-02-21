package com.pearadmin.pro.common.tools.support.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 内存分页请求对象
 *
 * @author 就眠仪式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

    /**
     * 当前页
     * */
    private int pageIndex;

    /**
     * 页数量
     * */
    private int pageSize;
}
