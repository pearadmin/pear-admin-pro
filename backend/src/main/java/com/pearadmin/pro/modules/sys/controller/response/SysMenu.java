package com.pearadmin.pro.modules.sys.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu {

    private String parent;

    private String path;

    private String name;

    private String type;

    private String icon;

    private String title;

    private Integer sort;

    private String link;

    private Boolean hidden;

    private Boolean status;

}
