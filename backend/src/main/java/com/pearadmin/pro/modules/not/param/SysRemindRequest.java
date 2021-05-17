package com.pearadmin.pro.modules.not.param;

import com.pearadmin.pro.common.web.base.page.PageRequest;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 提醒列表 -- 参数实体
 * <p>
 * author: 就眠仪式
 * date: 2021-04-01
 */
@Data
@Alias("SysRemindRequest")
public class SysRemindRequest extends PageRequest {

}
