package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SysUserRequest")
public class SysUserRequest extends PageRequest {

    /**
     * 用户名
     * */
    private String username;

    /**
     * 邮箱
     * */
    private String email;

}
