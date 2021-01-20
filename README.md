## PatrickStarCloud
派大星的微服务!!!

关联项目: https://github.com/WswSummer15/SummerCloud

移除spring.cloud.alibaba依赖以及alibaba所有开源组件后重构

## Java版本

```text
Java1.8
```

## 框架版本

```text
<spring.boot.version>2.2.2.RELEASE</spring.boot.version>
<spring.cloud.version>Hoxton.SR1</spring.cloud.version>
```

## SpringCloud组件选择

```text
1. 注册中心 consul
2. 配置中心 consul
3. 服务调用 OpenFeign
4. 负载均衡 Ribbon
5. 服务限流降级 Hystrix
6. 网关 Gateway
7. 服务总线 SpringCloud Bus
8. 缓存中间件 Redis
9. 消息中间件 RabbitMQ
```

## 项目结构

```text
patrickstar-gateway-service -> 4000
patrickstar-auth-service -> 3000
patrickstar-task-service -> 4001
patrickstar-recepienter-service -> 4002
```

## 程序运行

### 本地环境

```text
最新版本即可
1. consul保存k/v配置启动
cd consul ->  .\consul agent -server -bootstrap-expect 1 -data-dir E:\consul\data -node=consulServer1 -bind 127.0.0.1 -ui -rejoin  -client 0.0.0.0

2. RabbitMQ
省略
3. Redis
省略
```

### 使用consul作为配置中心管理配置文件

配置文件格式: config/patrickstar-bigsmart-service/data

#### 1. patrickstar-task-service配置文件

```yaml
server:
  port: 4001

spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://***:3306/task-system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: ***
    password: ***
    hikari:
      minimum-idle: 5
      max-lifetime: 1800000
      maximum-pool-size: 15
      auto-commit: true
      idle-timeout: 30000
      pool-name: DatebookHikariCP
      connection-timeout: 30000

  redis:
    host: ***
    port: 6379
    lettuce:
      pool:
        # 连接池最大连接数(使用负值表示没有限制) 默认为8
        max-active: 8
        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1
        max-wait: -1ms
        # 连接池中的最大空闲连接 默认为8
        max-idle: 8
        # 连接池中的最小空闲连接 默认为 0
        min-idle: 0

  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest
    publisher-confirms: true
    publisher-returns: true
    template:
      # 只要消息抵达队列,以异步方式优先回调returnsConfirm
      mandatory: true
    listener:
      simple:
        # 消费端手动ack消息
        acknowledge-mode: manual

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.wsw.patrickstar.entity

redisson:
  client:
    address: redis://***:6379

management:
  endpoints:
    web:
      exposure:
        include: '*' 
```

#### 2. patrickstar-auth-service配置文件

```yaml
server:
  port: 3000

spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://***:3306/task-system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: ***
    password: ***
    hikari:
      minimum-idle: 5
      max-lifetime: 1800000
      maximum-pool-size: 15
      auto-commit: true
      idle-timeout: 30000
      pool-name: DatebookHikariCP
      connection-timeout: 30000
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true

  redis:
    host: ***
    port: 6379
    lettuce:
      pool:
        # 连接池最大连接数(使用负值表示没有限制) 默认为8
        max-active: 8
        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1
        max-wait: -1ms
        # 连接池中的最大空闲连接 默认为8
        max-idle: 8
        # 连接池中的最小空闲连接 默认为 0
        min-idle: 0

jwt:
  secretKey: ***

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

#### 3. patrickstar-gateway-service配置文件

```yaml
spring:
  redis:
    host: ***
    port: 6379
    lettuce:
      pool:
        # 连接池最大连接数(使用负值表示没有限制) 默认为8
        max-active: 8
        # 连接池最大阻塞等待时间(使用负值表示没有限制) 默认为-1
        max-wait: -1ms
        # 连接池中的最大空闲连接 默认为8
        max-idle: 8
        # 连接池中的最小空闲连接 默认为 0
        min-idle: 0
jwt:
  secretKey: ***
```

#### 4. patrickstar-recepienter-service配置文件

```yaml
server:
  port: 4002

spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://***:3306/task-system?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: ***
    password: ***
    hikari:
      minimum-idle: 5
      max-lifetime: 1800000
      maximum-pool-size: 15
      auto-commit: true
      idle-timeout: 30000
      pool-name: DatebookHikariCP
      connection-timeout: 30000
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true

  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest
    publisher-confirms: true
    publisher-returns: true
    template:
      # 只要消息抵达队列,以异步方式优先回调returnsConfirm
      mandatory: true
    listener:
      simple:
        # 消费端手动ack消息
        acknowledge-mode: manual

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

配置文件在配置中心配置好后即可依次启动服务。

### 访问服务

将其他服务的端口写在配置中心，只通过4000端口的gateway网关暴露服务,通过网关访问具体服务即可,比如访问patrickstar-task-service微服务:

```text
http://localhost:4000/patrickstar-task-service/task/***
```
***
将认证中心独立成了微服务，将token的验证从主服务移到网关服务patrickstar-gateway-service中统一鉴权
