package com.pearadmin.pro.common.context;

import com.alibaba.fastjson.JSON;
import com.pearadmin.pro.common.aop.lang.enums.Action;
import com.pearadmin.pro.common.tools.core.SecureUtil;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.modules.sys.service.SysLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Describe: Base Context
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class BaseContext {

    @Resource
    private SysLogService sysLogService;

    /**
      * 日 志 记 录
      * */
    @Async
    public void record(String title, String describe, Action action, Boolean state ,String result, String error) {
       SysLog sysLog = new SysLog();
       sysLog.setTitle(title);
       sysLog.setDescribe(describe);
       sysLog.setAction(action);
       sysLog.setOperator(SecureUtil.getNickName());
       sysLog.setType(ServletUtil.getMethod());
       sysLog.setUrl(ServletUtil.getRequestURI());
       sysLog.setBrowser(ServletUtil.getBrowser());
       sysLog.setCreateBy(SecureUtil.getUserId());
       sysLog.setCreateTime(LocalDateTime.now());
       sysLog.setUpdateBy(SecureUtil.getUserId());
       sysLog.setUpdateTime(LocalDateTime.now());
       sysLog.setAddress(ServletUtil.getRemoteHost());
       sysLog.setSystem(ServletUtil.getSystem());
       sysLog.setState(state);
       sysLog.setError(error);
       sysLog.setResult(JSON.toJSONString(result));
       sysLogService.save(sysLog);
    }
}
