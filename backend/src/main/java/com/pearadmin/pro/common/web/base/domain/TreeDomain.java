package com.pearadmin.pro.common.web.base.domain;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import java.util.List;

@Data
public class TreeDomain<T> extends BaseDomain{

    /**
     * 父级编号
     * */
    private String parent;

    /**
     * 子级集合
     * */
    @TableField(exist = false)
    private List<T> children;

}
