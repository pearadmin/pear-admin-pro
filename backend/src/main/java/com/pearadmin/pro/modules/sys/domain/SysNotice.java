package com.pearadmin.pro.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("SysNotice")
@TableName("sys_notice")
public class SysNotice {

    /**
     * 编号
     * */
    private String id;

    /**
     * 标题
     * */
    private String title;

    /**
     * 内容
     * */
    private String content;

}
