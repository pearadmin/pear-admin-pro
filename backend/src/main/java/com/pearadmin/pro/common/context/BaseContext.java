package com.pearadmin.pro.common.context;

import com.alibaba.fastjson.JSON;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.common.aop.lang.enums.Action;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.modules.sys.service.SysConfigService;
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

    // 日 志 服 务
    @Resource
    private SysLogService sysLogService;

    // 配 置 服 务
    @Resource
    private SysConfigService sysConfigService;

    // 用 户 上 下 文
    @Resource
    private UserContext userContext;

    /**
     * 记录日志
     * @param title 标题
     * @param describe 描述
     * @param action 动作
     * @param state 状态
     * @param result 结果
     * @param error 异常
     * */
    @Async
    public void record(String title, String describe, Action action, Boolean state ,String result, String error) {
       SysLog sysLog = new SysLog();
       sysLog.setTitle(title);
       sysLog.setDescribe(describe);
       sysLog.setAction(action);
       sysLog.setOperator(userContext.getNickName());
       sysLog.setType(ServletUtil.getMethod());
       sysLog.setUrl(ServletUtil.getRequestURI());
       sysLog.setBrowser(ServletUtil.getBrowser());
       sysLog.setCreateBy(userContext.getUserId());
       sysLog.setCreateTime(LocalDateTime.now());
       sysLog.setUpdateBy(userContext.getUserId());
       sysLog.setUpdateTime(LocalDateTime.now());
       sysLog.setAddress(ServletUtil.getRemoteHost());
       sysLog.setSystem(ServletUtil.getSystem());
       sysLog.setState(state);
       sysLog.setError(error);
       sysLog.setResult(JSON.toJSONString(result));
       sysLogService.save(sysLog);
    }

    /**
     * 邮件发送
     * */
    public void sendEmail(){

        //

    }

    /**
     * 短信发送
     * */
    public void sendSms(){

        //
    }
}
