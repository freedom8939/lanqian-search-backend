# 蓝签-智搜平台
> github地址： https://github.com/freedom8939/lanqian-search-backend
## 在线访地址:
> http://www.lanshuqian.top/
## 项目介绍
    该平台基于 Spring Boot、Elastic Stack 和 Vue 3，提供一站式信息聚合搜索服务。用户可以在同一页面集中搜索来自不同来源和不同类型的内容，极大地提升了搜索体验。同时，企业可以将各项目的数据接入该搜索平台，复用统一的搜索后端，从而提高开发效率并降低系统维护成本。

## 技术选型
### 后端
+ JDK8.0
+ Elasticsearch 7.17.25
+ Spring Boot 2.7 框架 + springboot-init 脚手架 
+ MySQL 数据库（8.x 版本）
+ Elastic Stack 
+ Elasticsearch 搜索引擎（重点） 
+ Logstash 数据管道 
+ Kibana 数据可视化 
+ 数据抓取（jsoup、HttpClient 爬虫） 
+ 离线 
+ 实时
+ 设计模式 
  + 门面模式 
  + 适配器模式 
  + 注册器模式 
+ 数据同步（4 种同步方式） 
  + 定时 
  + 双写 
  + Logstash 
  + Canal 
+ JMeter 压力测试


### 前端
+ Vue 3
+ Ant Design Vue 组件库
+ 页面状态同步

