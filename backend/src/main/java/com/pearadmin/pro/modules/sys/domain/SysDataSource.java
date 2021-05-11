package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 数据源模型
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */

@Data
@Alias("SysDataSource")
@TableName("sys_data_source")
@EqualsAndHashCode(callSuper = true)
public class SysDataSource extends BaseDomain {

    /**
     * 编号
     * */
    private String id;

    /**
     * 名称
     * */
    private String name;

    /**
     * 是否启用
     * */
    private Boolean enable;

    /**
     * 账户
     * */
    private String username;

    /**
     * 密码
     * */
    private String password;

    /**
     * 链接
     */
    private String url;

    /**
     * 驱动
     * */
    private String driver;

}
