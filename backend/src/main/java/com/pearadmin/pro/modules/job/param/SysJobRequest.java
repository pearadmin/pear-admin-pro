package com.pearadmin.pro.modules.job.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;

@Data
public class SysJobRequest extends PageRequest {

    /**
     * 任务名称
     * */
    private String name;
}
