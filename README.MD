## 系统简介
这是一个简单高效的后台权限管理系统。本项目采用springboot作为框架搭建而成，消除了繁杂的XML配置，使得二次开发更为简单；数据访问层采用Mybatis，同时引入了通用tkMybatis和PageHelper插件，对后台mapper层增删改查的书写以及分页功能提供了便利，消除了大量传统XML配置SQL的代码；安全框架采用了Shiro框架，可实现对按钮级别的权限控制；前端页面使用Bootstrap构建，主题风格为时下Google的Material Design，并提供多套配色以供选择。


## 功能模块
系统功能模块组成如下所示：
```
├─系统管理
│  ├─用户管理
│  ├─菜单管理
│  ├─角色管理
│  └─部门管理
├─系统监控
│  ├─在线用户
│  └─系统日志
```
## 技术选型
### 后端

- 基础框架：Spring Boot

- 持久层框架：Mybatis

- 安全框架：Apache Shiro

- 摸板引擎：Thymeleaf

- 数据库连接池：阿里巴巴Druid

- 日志打印：logback

- 其他：fastjson,MD5等

### 前端

- 基础框架：Bootstrap

- JavaScript框架：jQuery

- 消息组件：Bootstrap notify

- 提示框插件：SweetAlert2

- 树形插件：jsTree

- 树形表格插件：jqTreeGrid

- 表格插件：BootstrapTable

- 表单校验插件：jQuery-validate

- 多选下拉框插件：multiple-select

- 图表插件：Highcharts


### 开发环境

- 语言：Java

- IDE：Myeclipse

- 依赖管理：Maven

- 数据库：MySQL 5.7

- 版本管理：SVN，git



## 使用说明

下载后以maven项目导入。

开发时直接使用Spring Boot的入口类启动即可，访问地址[localhost](localhost):8080。账号xxd，密码123。



## 留言

本项目是我学习springboot时所开发， 通过本项目对springboot框架以及shiro安全框架有了新的认识。

有任何问题请留言给我， 我会及时回复的。

