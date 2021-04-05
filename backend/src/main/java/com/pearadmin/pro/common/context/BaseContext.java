package com.pearadmin.pro.common.context;

import com.alibaba.fastjson.JSON;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.common.aop.enums.Action;
import com.pearadmin.pro.common.tools.core.ServletUtil;
import com.pearadmin.pro.modules.sys.service.SysLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Base Context
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
@Component
public class BaseContext {

    /**
     * 日 志 服 务
     * */
    @Resource
    private SysLogService sysLogService;

    /**
     * 用 户 上 下 文
     * */
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
    public void record(String title, String describe, Action action, Boolean state , String result, String error) {
       SysLog sysLog = new SysLog();
       sysLog.setTitle(title);
       sysLog.setAction(action);
       sysLog.setDescribe(describe);
       sysLog.setType(ServletUtil.getMethod());
       sysLog.setUrl(ServletUtil.getRequestURI());
       sysLog.setBrowser(ServletUtil.getBrowser());
       sysLog.setCreateBy(userContext.getUserId());
       sysLog.setAddress(ServletUtil.getRemoteHost());
       sysLog.setOperator(userContext.getNickName());
       sysLog.setResult(JSON.toJSONString(result));
       sysLog.setUpdateBy(userContext.getUserId());
       sysLog.setCreateTime(LocalDateTime.now());
       sysLog.setUpdateTime(LocalDateTime.now());
       sysLog.setSystem(ServletUtil.getSystem());
       sysLog.setState(state);
       sysLog.setError(error);
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

        
    }

    /**
     * 文件存储
     * */
    public void upload(){

    }
}
