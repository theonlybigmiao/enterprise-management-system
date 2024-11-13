# Enterprise Management System

## 项目简介
本项目搭建了一个简易的企业管理系统。前端采用vue3+element-plus，后端采用Spring Boot，
数据库管理则使用Navicat Premium。可选择超级管理员或企业管理员进行登录，
两种管理员分别可以对所有企业/本企业所有部门进行增删改查操作，企业/部门信息以树状表格层次展示。
该系统支持修改个人资料，如用户名、邮箱等，还支持修改密码。
这是初学者的作品，对于同为新手的社区成员或许具备一定的参考/借鉴价值。

运行效果见department.png，basic_info.png，personal_page.png。

## 主要项目结构

```
│  .gitignore
|
|——前端
│  router
|     router.js 页面切换的路由配置
|  views
|     basic.vue 可修改个人资料和密码
|     mine.vue  展示个人信息
|     department.vue 展示企业/部门列表
|     login.vue 登录界面
|     signup.vue 注册，需使用验证码
|  App.vue 初始界面容器
|  main.js 前端启动配置
|  package.json 前端依赖配置
|  verificationCode.json 验证码json文件
|
├─后端
│   controller 放置所有Controller类
│   biz 放置所有Biz类
|   mapper 放置所有Mapper类
│   entity 放置所有实体类
|     ceuser.java 用户类(个人)
|     department.java 部门类
|     renter.java 租户类(企业)
|   util 异常与拦截器类
|   DemoApplication 启动Spring Boot
|   pom.xml
|   resource 资源文件
|     application.properties 后端依赖配置文件
```

## 环境配置
本项目采用前后端分离模式。
- 安装vue3和element-plus，将“frontend”文件夹部署到HBuilder或VS Code。安装axios，本项目版本为1.7.2。
- 在IDEA中安装Spring Boot和MyBatis依赖。此项目所用的IDEA版本为Community Edition 2022.3.2，
Spring Boot版本为2.4.2，MyBatis版本为3.5.0。
- 新建Springt Boot项目，将“backend”文件夹部署到项目中。可直接使用文件夹内的pm.xml
和 /resource/application.properties。安装Navicat Premium并获取使用权限，这里选择Navicat Premium 16。记下初始密码。
- 建立自己的库，按entity文件夹中的java类在其中建立对应表。在/resource/application.properties中修改"your_password"为数据库密码，修改"your_database"为数据库名称。
- 对于后端，端口号可在application.properties中改变，其他依赖项可在pom.xml中改变。


## 项目运行

- 在ceuser表中初始化超级管理员信息(超级管理员不可注册)。

- 后端运行DemoApplication，前端从login.vue启动。

- 可直接使用超级管理员信息登录，
  也可注册成为企业管理员，然后可以管理企业或部门/修改信息。


## 开源协议

本仓库下的作品若无特殊说明均采用 [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html) 开源协议进行许可
