# Pear Single

#### 软件介绍

基于 SpringBoot 2.0 与 Spring Security 的 JWT 登陆实现方案

#### 基本流程

![输入图片说明](https://images.gitee.com/uploads/images/2019/1211/192830_73aa0a3b_4835367.png "单点登陆流程.png")

#### 使用说明

本次演示，使用PostMan 模拟请求，测试项目

不携带Token去访问接口

![输入图片说明](https://images.gitee.com/uploads/images/2019/1211/192929_a2bebc9e_4835367.png "没有登陆.png")

调用登陆接口，返回 Token 信息

![输入图片说明](https://images.gitee.com/uploads/images/2019/1211/193102_76d1b13f_4835367.png "登陆成功.png")‘

携带Token,再次访问接口

![输入图片说明](https://images.gitee.com/uploads/images/2019/1211/193128_f7554518_4835367.png "访问成功.png")

本项目可根据相关需求，修改为相应的前后端分离后台， Spring Cloud 网关等来集成单点登陆做认证权限管理

项目内注释简单明确，可供学习 商用 开发