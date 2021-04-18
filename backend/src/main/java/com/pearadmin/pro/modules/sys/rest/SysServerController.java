package com.pearadmin.pro.modules.sys.rest;

import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.tools.support.server.Server;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.common.web.domain.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器控制器
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/23
 * */
@Api(tags = {"服务"})
@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "server")
public class SysServerController extends BaseController {

    /**
     * 运行环境
     *
     * param param
     * return Result
     * */
    @GetMapping("info")
    public Result server() throws Exception{
        Server server = new Server();
        server.copyTo();
        return success(server);
    }
}
