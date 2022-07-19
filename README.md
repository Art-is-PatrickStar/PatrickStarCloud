## PatrickStarCloud
PatrickStar is here!!!

使用Consul作为注册/配置中心请选择分支: patrick-star-consul-main

使用Nacos作为注册/配置中心请选择分支: patrick-star-nacos-main
## Java版本

```text
Java1.8
```

## 框架版本

```text
<spring.boot.version>2.2.2.RELEASE</spring.boot.version>
<spring.cloud.version>Hoxton.SR1</spring.cloud.version>
<spring.cloud.alibaba.version>2.1.0.RELEASE</spring.cloud.alibaba.version>
```

## SpringCloud组件选择

```text
1. 注册中心 Consul/Nacos
2. 配置中心 Consul/Nacos
3. 服务调用 OpenFeign
4. 负载均衡 Ribbon
5. 服务限流降级 Hystrix/Sentinel
6. 网关 Gateway
7. 缓存中间件 Redis
8. 消息中间件 RabbitMQ
9. 搜索引擎 ElasticSearch
```

## 项目结构

```text
patrickstar-gateway -> 4000
patrickstar-auth -> 3000
patrickstar-taskEntity-service -> 4001
patrickstar-taskRecord-service -> 4002
patrickstar-search-service -> 4003
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

or

最新版本即可
1. Nacos
cd bin -> .\startup.cmd

2. Sentinel(jar包)
自定义端口启动 -> java -jar .\sentinel-dashboard-1.8.0.jar --server.port=9090

3. RabbitMQ
省略
4. Redis
省略
```

### 使用Consul/Nacos作为配置中心管理配置文件

配置文件格式Consul: config/patrickstar-taskEntity-service/data

配置文件格式Nacos: {微服务名称}-{环境}.{文件格式} 例如: patrickstar-taskEntity-service-dev.yaml

#### 1. patrickstar-taskEntity-service配置文件

```yaml
spring:
  shardingsphere:
    datasource:
      names: ds0
      # 配置数据源
      ds0:
        # 数据库连接池类名称
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        # 数据库url连接
        jdbcUrl: jdbc:mysql://***:3306/patrickstar-taskEntity?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
        #数据库用户名
        username: ***
        # 数据库密码
        password: ***
        hikari:
          minimum-idle: 5
          max-lifetime: 1800000
          maximum-pool-size: 15
          auto-commit: true
          idle-timeout: 30000
          pool-name: DatebookHikariCP
          connection-timeout: 30000
    # 配置分表规则
    sharding:
      tables:
        taskEntity:
          actual-data-nodes: ds0.task_$->{2020..2021}
          # 配置分表策略
          table-strategy:
            standard:
              #分片列名称
              sharding-column: create_date
              #分片算法行表达式
              precise-algorithm-class-name: com.wsw.patrickstar.algorithm.PreciseYearTableShardingAlgorithm
              range-algorithm-class-name: com.wsw.patrickstar.algorithm.RangeYearTableShardingAlgorithm
          # 主键策略 雪花算法
          key-generator:
            column: task_id
            type: SNOWFLAKE
      defaultDataSourceName: ds0
    # 打开sql控制台输出日志
    props:
      sql:
        show: true
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
    listener:
      simple:
        # 消费端手动ack消息
        acknowledge-mode: manual
        # 是否支持重试
        retry.enabled: true
  kafka:
    listener:
      # 批量监听开启
      type: batch
      # 在侦听器容器中运行的线程数
      concurrency: 3
      # 当enable.auto.commit的值设置为false时,该值会生效,为true时不会生效
      # 每处理完业务手动调用Acknowledgment.acknowledge()后立即提交
      ack-mode: manual_immediate
    # 消费者全局配置
    consumer:
      bootstrap-servers: 127.0.0.1:9092
      group-id: taskService-consumer
      # 是否开启自动提交 offset 功能
      enable-auto-commit: false
      # 如果'enable.auto.commit'为true,则消费者偏移自动提交给Kafka的频率(毫秒)
      auto-commit-interval: 1000
      # key反序列化
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      # value反序列化
      value-deserializer: org.apache.kafka.common.serialization.ByteArrayDeserializer
      # 当Kafka中没有初始偏移量或者服务器上不再存在当前偏移量时该怎么办，默认值为latest，表示自动将偏移重置为最新的偏移量
      auto-offset-reset: earliest
      # 一次调用poll()操作时返回的最大记录数
      max-poll-records: 10
      properties:
        session.timeout.ms: 15000
        retry.backoff.ms: 100
    # 生产者全局配置
    producer:
      bootstrap-servers: 127.0.0.1:9092
      # key序列化
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      # value序列化
      value-serializer: org.apache.kafka.common.serialization.ByteArraySerializer
      # 当所有的follower都同步消息成功后发送ack。不仅是主的分区将消息保存成功了，而且其所有的分区的副本数也都同步好了，才会被认为发动成功。
      acks: -1
      # 当producer接收到error ACK,或者没有接收到ACK时,允许消息重发的次数
      retries: 3
  mail:
    host: smtp.qq.com
    username: 2544894086@qq.com
    password: ***
    default-encoding: UTF-8
    properties:
      mail:
        smtp:
          port: 465
          starttls:
            enable: true
            required: true
          ssl:
            enable:
              true
        debug: false

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.wsw.patrickstar.entity

mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

redisson:
  client:
    address: redis://***:6379
    
minio:
  url: http://***:9000
  accessKey: ***
  secretKey: ***
  bucketName: test

# xxl-job配置
xxl:
  job:
    ### xxl-job admin address list, such as "http://address" or "http://address01,http://address02"
    admin:
      addresses: http://127.0.0.1:8088/xxl-job-admin
    ### xxl-job, access token
    accessToken:
    ### xxl-job executor appname
    executor:
      appname: patrick-star-wsw-taskEntity-excutor
      ### xxl-job executor registry-address: default use address to registry , otherwise use ip:port if address is null
      address:
      ### xxl-job executor server-info
      ip:
      port: 9999
      ### xxl-job executor log-path
      logpath: /data/applogs/xxl-job/jobhandler
      ### xxl-job executor log-retention-days
      logretentiondays: 7

management:
  endpoints:
    web:
      exposure:
        include: '*' 
```

#### 2. patrickstar-auth配置文件

```yaml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://***:3306/patrickstar-user?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
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

#### 3. patrickstar-gateway配置文件

```yaml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://***:3306/patrickstar-user?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
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

jwt:
  secretKey: ***
```

#### 4. patrickstar-taskRecord-service配置文件

```yaml
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://***:3306/patrickstar-taskRecord?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
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

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

#### 5. patrickstar-search-service配置文件

```yaml
spring:
  data:
    elasticsearch:
      cluster-nodes: localhost:9300
      cluster-name: Summer15-cluster
      repositories:
        enabled: true
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    url: jdbc:mysql://***:3306/patrickstar-taskEntity?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
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
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: guest
    password: guest
    publisher-confirms: true
    publisher-returns: true
    listener:
      simple:
        acknowledge-mode: manual # 消费端手动ack消息
        retry:
          enabled: true # 允许消息消费失败的重试
          max-attempts: 3 # 消息最多消费次数3次
          initial-interval: 2000 # 消息多次消费的间隔2秒

management:
  endpoints:
    web:
      exposure:
        include: '*'
  health:
    elasticsearch:
      response-timeout: 3s
```

配置文件在配置中心配置好后即可依次启动服务。

### 访问服务

通过4000端口的gateway网关暴露服务,通过网关访问具体服务即可,比如访问patrickstar-taskEntity-service微服务:

```text
http://localhost:4000/patrickstar-taskEntity-service/taskEntity/***
```

### 认证
将认证中心独立成了微服务，将token的验证从主服务移到网关服务patrickstar-gateway中统一鉴权

### mysql与es数据同步
DB ES 双写, ES 只做搜索功能, 使用RabbitMQ队列处理数据同步
![image](https://user-images.githubusercontent.com/34562805/106081036-2ff99e00-6153-11eb-9e00-957ffa7d434c.png)
只向MQ传递id,不传递业务数据

### 使用logstash将已存在于数据库的数据一次性同步到es
配置文件:

**mysql.conf**
```text
input {
  jdbc {
	# mysql jdbc connection string to our backup databse
	jdbc_connection_string => "jdbc:mysql://***:3306/patrickstar-taskEntity"
	# the user we wish to excute our statement as
	jdbc_user => "***"
	jdbc_password => "***"
	# the path to our downloaded jdbc driver
	jdbc_driver_library => "C:/Program Files (x86)/MySQL/Connector J 8.0/mysql-connector-java-8.0.20.jar"
	# the name of the driver class for mysql
	jdbc_driver_class => "com.mysql.cj.jdbc.Driver"
	jdbc_paging_enabled => "true"
	jdbc_page_size => "10000"
	# 以下对应着要执行的sql的绝对路径
	statement_filepath => "E:/logstash-7.10.1/mysql/taskEntity.sql"
	# 定时字段 各字段含义（由左至右）分、时、天、月、年，全部为*默认含义为每分钟都更新
	schedule => "* * * * *"
	# 设定ES索引类型
	type => "_doc"
	# Database field name from uppercase to lowercase
	lowercase_column_names => false
  }
}

filter {
  # 过滤指定字段
  mutate {
    remove_field => ["@version"]
	remove_field => ["@timestamp"]
	remove_field => ["type"]
  }
  json {
	source => "message"
	remove_field => ["message"]
  }
}

output {
  elasticsearch {
	#ESIP地址与端口
	hosts => "127.0.0.1:9200"
	# ES索引名称（自己定义的）
	index => "taskEntity"
	# 自增ID编号
	document_id => "%{taskId}"
  }
  stdout {
	# 以JSON格式输出
	codec => json_lines
  }
}
```
**taskEntity.sql**
```sql
select task_id as taskId, task_name as taskName, task_caption as taskCaption, create_date as createDate, 
task_status as taskStatus, recepient_id as recepientId, recepient_name as recepientName, tester_id as testerId, 
tester_name as testerName, archive, modify_date as modifyDate
from taskEntity
```
启动:
>bin/logstash -f ../logstash-7.10.1/mysql/mysql.conf

效果:
![image](https://user-images.githubusercontent.com/34562805/106107277-b2995200-6181-11eb-84be-525a802b03ac.png)
![image](https://user-images.githubusercontent.com/34562805/106107351-ccd33000-6181-11eb-9bba-f2a19b9c0fa7.png)
![image](https://user-images.githubusercontent.com/34562805/106107426-e2485a00-6181-11eb-9199-f01a12490a3a.png)
