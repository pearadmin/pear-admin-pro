package com.pearadmin.pro.common.tools.core;

import com.pearadmin.pro.common.tools.support.page.PageRequest;
import com.pearadmin.pro.common.tools.support.page.PageResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Page 分页 Utils 函数
 *
 * @author 就眠仪式
 * */
public class PageUtil {

    private PageUtil(){ }

    /**
     * 内存分页
     *
     * @param records  待分页的数据
     * @param pageIndex  当前页码
     * @param pageSize 每页显示的条数
     * @return 分页之后的数据
     */
    public static <T> List<T> pagination(List<T> records, int pageIndex, int pageSize) {
        if (records==null) {
            return Collections.emptyList();
        }
        int totalCount = records.size();
        int remainder = totalCount % pageSize;
        int pageCount = (remainder > 0) ? totalCount/pageSize + 1 : totalCount/pageSize;
        if (remainder == 0) {
            return records.stream().skip((pageIndex - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        } else {
            if (pageIndex == pageCount) {
                return records.stream().skip((pageIndex - 1) * pageSize).limit(totalCount).collect(Collectors.toList());
            } else {
                return records.stream().skip((pageIndex - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
            }
        }
    }

    /**
     * 内存分页
     *
     * @param records  待分页的数据
     * @param pageIndex  当前页码
     * @param pageSize 每页显示的条数
     * @return 分页之后的数据
     */
    public static <K,T> Map<K,T>  pagination(Map<K,T> records, int pageIndex, int pageSize) {
        if (records==null) {
            return new HashMap<>();
        }
        int totalCount = records.size();
        int remainder = totalCount % pageSize;
        int pageCount = (remainder > 0) ? totalCount/pageSize + 1 : totalCount/pageSize;
        List<K> keys;
        if (remainder == 0) {
            keys = records.keySet().stream().skip((pageIndex - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        } else {
            if (pageIndex == pageCount) {
                keys = records.keySet().stream().skip((pageIndex - 1) * pageSize).limit(totalCount).collect(Collectors.toList());
            } else {
                keys = records.keySet().stream().skip((pageIndex - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
            }
        }
        return records.entrySet().stream().filter(item -> keys.contains(item.getKey())).collect(Collectors.toMap(item->item.getKey(),item->item.getValue()));
    }

    /**
     * 内存分页
     *
     * @param records  待分页的数据
     * @param request  当前页码
     * @return 分页之后的数据
     */
    public static <T> PageResponse<List<T>> pagination(List<T> records, PageRequest request) {
        int pageSize = request.getPageSize();
        int pageIndex = request.getPageIndex();
        List<T> result =  pagination(records,pageIndex,pageSize);
        PageResponse<List<T>> pageInfo = new PageResponse<>(records.size(),pageIndex,result);
        return pageInfo;
    }

    /**
     * 内存分页
     *
     * @param records  待分页的数据
     * @param request  分页参数
     * @return response 分页结果
     */
    public static <K,T> PageResponse<Map<K,T>> pagination(Map<K,T> records, PageRequest request) {
        int pageSize = request.getPageSize();
        int pageIndex = request.getPageIndex();
        Map<K,T> result =  pagination(records,pageIndex,pageSize);
        PageResponse<Map<K,T>> pageInfo = new PageResponse<>(records.size(),pageIndex,result);
        return pageInfo;
    }
}
