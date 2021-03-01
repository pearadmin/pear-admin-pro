package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pearadmin.pro.common.web.base.BaseDomain;
import com.pearadmin.pro.common.web.domain.Result;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Describe: 岗位领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
@Alias("SysPost")
@TableName("sys_post")
public class SysPost extends BaseDomain {

    /**
     * 编号
     * */
    private String id;

    /**
     * 编码
     * */
    private String code;

    /**
     * 名称
     * */
    private String name;

    /**
     * 排序
     * */
    private Integer sort;

    /**
     * 启用
     * */
    private Boolean enable;

}
