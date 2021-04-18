package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;

@Data
public class SysConfigRequest extends PageRequest {

    /**
     * 配置名
     * */
    private String name;
}
