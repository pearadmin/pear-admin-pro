package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;

/**
 * 配置列表 -- 参数实体
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/01
 */
@Data
public class SysConfigRequest extends PageRequest {

    /**
     * 配置名
     * */
    private String name;
}
