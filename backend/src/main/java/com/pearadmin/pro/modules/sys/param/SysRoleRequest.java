package com.pearadmin.pro.modules.sys.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SysRoleRequest")
public class SysRoleRequest extends PageRequest {

    /**
     * 角色名称
     */
    private String name;
}
