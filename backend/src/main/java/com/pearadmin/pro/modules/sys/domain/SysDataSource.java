package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.domain.BaseDomain;
import lombok.Data;
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
public class SysDataSource extends BaseDomain {

    private String id;

    private String name;

    private String type;

    private String username;

    private String password;

    private String url;

    private String driver;

    private String code;

}
