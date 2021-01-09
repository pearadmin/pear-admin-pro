package com.pearadmin.pro.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pearadmin.pro.common.constant.ControllerConstant;
import com.pearadmin.pro.common.web.base.BaseController;
import com.pearadmin.pro.modules.sys.domain.SysUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstant.PREFIX_SYS + "power")
public class SysPowerController extends BaseController {

    @GetMapping("data")
    public void data(){

    }

    @GetMapping("page")
    public void page(IPage page){

    }

    @GetMapping("get")
    public void get(String id){

    }

    @PostMapping("save")
    public void save(@RequestBody SysUser sysPower){

    }

    @PutMapping("edit")
    public void edit(@RequestBody SysUser sysPower){

    }

    @DeleteMapping("remove")
    public void remove(String id){

    }

    @DeleteMapping("batchRemove")
    public void batchRemove(String ids){

    }
}
