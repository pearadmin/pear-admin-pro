package com.pearadmin.pro.generate.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("GenTableRequest")
public class GenTableRequest extends PageRequest {

    private String tableName;

    private String tableComment;
}
