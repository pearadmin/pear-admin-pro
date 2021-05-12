package com.pearadmin.pro.modules.not.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;

@Data
public class SysAnnounceRequest extends PageRequest {

    /**
     * 公告标题
     * */
    private String title;
}
