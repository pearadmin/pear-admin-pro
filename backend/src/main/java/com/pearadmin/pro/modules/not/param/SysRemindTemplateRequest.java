package com.pearadmin.pro.modules.not.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 模板列表 -- 参数实体
 * <p>
 * author: 就眠仪式
 * date: 2021-04-01
 */
@Data
@Alias("SysRemindTemplateRequest")
public class SysRemindTemplateRequest extends PageRequest {

}
