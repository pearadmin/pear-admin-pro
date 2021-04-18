package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;

@Data
public class SysLogRequest extends PageRequest {

    /**
     * 标题
     */
    private String title;
}
