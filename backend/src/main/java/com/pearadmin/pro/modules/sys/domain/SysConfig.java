package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SysConfig")
@TableName("sys_config")
public class SysConfig extends BaseDomain {

    /** 编号 */
    private String id;

    /** 名称 */
    private String name;

    /** 键 */
    private String key;

    /** 值 */
    private String value;

}
