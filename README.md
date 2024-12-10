# spring-cloud-netflix-example

#### spring cloud 学习示例

> Spring Cloud Netflix版

技术栈

SpringBoot 2.3.1.RELEASE

SpringCloud Hoxton.SR6

Eureka + Spring Cloud OpenFeign + Ribbon + Zuul + Hystrix + Zipkin + Spring Cloud Config + Rabbitmq + MybatisPlus + Redis


##### 1. 环境准备

| 服务       | 版本                |
|----------|-------------------|
| zipkin   | 2.27              |
| mysql    | 8.0.27            |
| redis    | 6.2.6             |
| rabbitmq | 3.9.12-management |

##### 2. 启动项目

1.修改config-server的账号密码

2.启动服务
    eureka-server
    config-server
    user
    gateway

3.访问
    http://localhost:7000/user/getServerName
