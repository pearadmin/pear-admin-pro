package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 数据字典 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 * */
@Data
public class SysDictDataRequest extends PageRequest {

    private String label;

    private String code;
}
