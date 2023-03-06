
## redis 

```bash
docker run --name cloud3-redis -d -p 6379:6379 redis
```

```bash
docker exec -it cloud3-redis bash
```

```bash 
redis-cli
```

## mysql

```bash
docker run -itd --name cloud3-mysql8 -p 3354:3306 -e MYSQL_ROOT_PASSWORD=root -e TZ=Asia/Shanghai  ubuntu/mysql:8.0-20.04_beta   --default-authentication-plugin=mysql_native_password
```



## knife4j 文档

http://localhost:8870/admin/doc.html



## rsa 秘钥对生成


```java
val pair = SecureUtil.generateKeyPair("RSA")
println(Base64.encode(pair.private.encoded))
println(Base64.encode(pair.public.encoded))
```
MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMFNdIpAQfPG5NxZDBLrIakZH0gyrsIfZRDq9bPeLJpTx4AWXICmqRvxLknN9HNNrncIhe7k5Q+ZRNKrIYJAUqaDyWmUKlUrd+yFjI/NLWXJt9lsvSrbTO0JN2StsFIRgg9WW4mqnI3Kw/ww4otO+wxCgZIhq8khF+eVsQlQDKFlAgMBAAECgYAfheGU8CCxQASveGgTJ7wgBuRGkllUd+kz5pU2BWvRqQr2NO9V3ZfjYiPxzj/ok8j5SW8KA9LlBdIm5th6lMHFdE/kfZUF6ZPaIQnxgvj779g59qgYh72gLgTw16KvQj/sJLS3zDXjG3QaDXPWiCICKiOHXb5h9yyD9S+uBWlOOwJBAOTfKEvTwVmNm1M6ms/OCY7uMM6OR6F/nigSwYCSeONAS/HauI3F3hOHmM4JCE3kbI/Dx+ntVY0jGj1FHVgzUOsCQQDYNv5uBCqghHaQ8hIw0vj+3WaU6W5HMzgPgG3wOhRrJ1JLXhvJ3DlUr/ZcnMpr5RHIqni13Rg4EnodeCs59sLvAkEAt6sCGn/vzR8fxBXmwNb2wrYRguGZSFgyorQSh0yEKNKjX46HxnZAtml9zfJfsKqcGG8kEm0814uQ9hRt7ScZSwJAEWISgJvbJX6g3bRom/5KQItiVPSThivrBOwJarfwrlDCAAc36rnG7jUbdISyjYwkKuLxw4VcDh4GLVpc91MMhwJBAOG2tF8dTL9IktueHg350XoSlyxGmVqztT3VPgfJ//w5n3Q538EU90R66OL99sCnTPytnC180yOAYpKruuMRhqo=
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBTXSKQEHzxuTcWQwS6yGpGR9IMq7CH2UQ6vWz3iyaU8eAFlyApqkb8S5JzfRzTa53CIXu5OUPmUTSqyGCQFKmg8lplCpVK3fshYyPzS1lybfZbL0q20ztCTdkrbBSEYIPVluJqpyNysP8MOKLTvsMQoGSIavJIRfnlbEJUAyhZQIDAQAB


## seata 整合

https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E


根据这里的版本说明我们选择seata1.6.1版本


### 资源参考

https://github.com/seata/seata/tree/master/script



### db创建



服务器

我们装备一个seata数据库

https://github.com/seata/seata/blob/master/script/server/db/mysql.sql



客户端



https://github.com/seata/seata/blob/master/script/client/at/db/mysql.sql



### 启动服务器



【修改配置】

  

 



![dbL68.png](https://i.328888.xyz/2023/03/06/dbL68.png)



复制application.example.yaml的部分内容到application.yaml





![djVjt.png](https://i.328888.xyz/2023/03/06/djVjt.png)



我们使用的是nacos，所以复制这块过来



完整的yaml



```yaml
#  Copyright 1999-2019 Seata.io Group.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#  http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

server:
  port: 7091

spring:
  application:
    name: seata-server

logging:
  config: classpath:logback-spring.xml
  file:
    path: ${user.home}/logs/seata
  extend:
    logstash-appender:
      destination: 127.0.0.1:4560
    kafka-appender:
      bootstrap-servers: 127.0.0.1:9092
      topic: logback_to_logstash

console:
  user:
    username: seata
    password: seata

seata:
  config:
    # support: nacos, consul, apollo, zk, etcd3
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      namespace: 2d62f31f-05a2-4be8-97b1-0dcd80693ac4
      group: dev 
      username: nacos
      password: nacos
      context-path:
      ##if use MSE Nacos with auth, mutex with username/password attribute
      #access-key:
      #secret-key:
      data-id: seataServer.properties
  registry:
    # support: nacos, eureka, redis, zk, consul, etcd3, sofa
    type: nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      group: dev
      namespace: 2d62f31f-05a2-4be8-97b1-0dcd80693ac4
      cluster: default
      username: nacos 
      password: nacos 
      context-path:
  store:
    # support: file 、 db 、 redis
    mode: db
    db:
      datasource: druid
      db-type: mysql
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://127.0.0.1:3354/seata?rewriteBatchedStatements=true
      user: root
      password: root
      min-conn: 10
      max-conn: 100
      global-table: global_table
      branch-table: branch_table
      lock-table: lock_table
      distributed-lock-table: distributed_lock
      query-limit: 1000
      max-wait: 5000
#  server:
#    service-port: 8091 #If not configured, the default is '${server.port} + 1000'
  security:
    secretKey: SeataSecretKey0c382ef121d778043159209298fd40bf3850a017
    tokenValidityInMilliseconds: 1800000
    ignore:
      urls: /,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-fe/public/**,/api/v1/auth/login
```



如果是使用mysql8 ，删除mysql5的驱动

![djFfP.png](https://i.328888.xyz/2023/03/06/djFfP.png)



【启动server】



windows：



```bash
seata-server.bat
```



启动后nacos就能看到一个seata-server的服务





### config配置



https://github.com/seata/seata/blob/master/script/config-center/config.txt



复制这里的内容，写入到nacos，名为`seataServer.properties`



修改我们的适配版本



```properties
#For details about configuration items, see https://seata.io/zh-cn/docs/user/configurations.html
#Transport configuration, for client and server
transport.type=TCP
transport.server=NIO
transport.heartbeat=true
transport.enableTmClientBatchSendRequest=false
transport.enableRmClientBatchSendRequest=true
transport.enableTcServerBatchSendResponse=false
transport.rpcRmRequestTimeout=30000
transport.rpcTmRequestTimeout=30000
transport.rpcTcRequestTimeout=30000
transport.threadFactory.bossThreadPrefix=NettyBoss
transport.threadFactory.workerThreadPrefix=NettyServerNIOWorker
transport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler
transport.threadFactory.shareBossWorker=false
transport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector
transport.threadFactory.clientSelectorThreadSize=1
transport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread
transport.threadFactory.bossThreadSize=1
transport.threadFactory.workerThreadSize=default
transport.shutdown.wait=3
transport.serialization=seata
transport.compressor=none

#Transaction routing rules configuration, only for the client
service.vgroupMapping.default_tx_group=default
#If you use a registry, you can ignore it
service.default.grouplist=127.0.0.1:8091
service.enableDegrade=false
service.disableGlobalTransaction=false

#Transaction rule configuration, only for the client
client.rm.asyncCommitBufferLimit=10000
client.rm.lock.retryInterval=10
client.rm.lock.retryTimes=30
client.rm.lock.retryPolicyBranchRollbackOnConflict=true
client.rm.reportRetryCount=5
client.rm.tableMetaCheckEnable=true
client.rm.tableMetaCheckerInterval=60000
client.rm.sqlParserType=druid
client.rm.reportSuccessEnable=false
client.rm.sagaBranchRegisterEnable=false
client.rm.sagaJsonParser=fastjson
client.rm.tccActionInterceptorOrder=-2147482648
client.tm.commitRetryCount=5
client.tm.rollbackRetryCount=5
client.tm.defaultGlobalTransactionTimeout=60000
client.tm.degradeCheck=false
client.tm.degradeCheckAllowTimes=10
client.tm.degradeCheckPeriod=2000
client.tm.interceptorOrder=-2147482648
client.undo.dataValidation=true
client.undo.logSerialization=jackson
client.undo.onlyCareUpdateColumns=true
server.undo.logSaveDays=7
server.undo.logDeletePeriod=86400000
client.undo.logTable=undo_log
client.undo.compress.enable=true
client.undo.compress.type=zip
client.undo.compress.threshold=64k
#For TCC transaction mode
tcc.fence.logTableName=tcc_fence_log
tcc.fence.cleanPeriod=1h

#Log rule configuration, for client and server
log.exceptionRate=100

#Transaction storage configuration, only for the server. The file, db, and redis configuration values are optional.
store.mode=db
store.lock.mode=db
store.session.mode=db
#Used for password encryption
#store.publicKey=

 

#These configurations are required if the `store mode` is `db`. If `store.mode,store.lock.mode,store.session.mode` are not equal to `db`, you can remove the configuration block.
store.db.datasource=druid
store.db.dbType=mysql
store.db.driverClassName=com.mysql.cj.jdbc.Driver
store.db.url=jdbc:mysql://127.0.0.1:3354/seata?useUnicode=true&rewriteBatchedStatements=true
store.db.user=root
store.db.password=root
store.db.minConn=5
store.db.maxConn=30
store.db.globalTable=global_table
store.db.branchTable=branch_table
store.db.distributedLockTable=distributed_lock
store.db.queryLimit=100
store.db.lockTable=lock_table
store.db.maxWait=5000

 

#Transaction rule configuration, only for the server
server.recovery.committingRetryPeriod=1000
server.recovery.asynCommittingRetryPeriod=1000
server.recovery.rollbackingRetryPeriod=1000
server.recovery.timeoutRetryPeriod=1000
server.maxCommitRetryTimeout=-1
server.maxRollbackRetryTimeout=-1
server.rollbackRetryTimeoutUnlockEnable=false
server.distributedLockExpireTime=10000
server.xaerNotaRetryTimeout=60000
server.session.branchAsyncQueueSize=5000
server.session.enableBranchAsyncRemove=false
server.enableParallelRequestHandle=false

#Metrics configuration, only for the server
metrics.enabled=false
metrics.registryType=compact
metrics.exporterList=prometheus
metrics.exporterPrometheusPort=9898
```





### client 配置



添加依赖



```kotlin
implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-seata:2022.0.0.0-RC1")
```

yaml配置



```yaml
seata:
  config:
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      group : dev
      namespace: 2d62f31f-05a2-4be8-97b1-0dcd80693ac4
      username: nacos
      password: nacos
      dataId: seataServer.properties
```

### 处理事务分组问题



修改下client的配置



```yaml
seata:
# 注意指定了这个需要server配置
  tx-service-group: cloud3_tx_group
  service:
    vgroup-mapping:
    # 我们自定义的事务分组
      cloud3_tx_group: default
  config:
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      group : dev
      namespace: 2d62f31f-05a2-4be8-97b1-0dcd80693ac4
      username: nacos
      password: nacos
      dataId: seataServer.properties
```



seataServer.properties里面要编写

```properties
service.vgroupMapping.cloud3_tx_group=default
```

重启seataServer，

否则会有错误

can not get cluster name in registry config 





这块我们可以写到nacos，所有的服务共享

```yaml
spring:
  application:
    name: admin
  profiles:
    active: dev
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: 2d62f31f-05a2-4be8-97b1-0dcd80693ac4
        group: dev
        username: nacos
        password: nacos
      config:
        server-addr: 127.0.0.1:8848
        namespace: 2d62f31f-05a2-4be8-97b1-0dcd80693ac4
        file-extension: yaml
        group: dev
        username: nacos
        password: nacos
#        共享配置
        extension-configs:
          - group: dev
            data-id: dubbo.yaml
            refresh: true
           # 只需要关注这里即可
          - group: dev
            data-id: seata.yaml
            refresh: true
```









## 参考资料


https://stackoverflow.com/questions/74614369/how-to-run-swagger-3-on-spring-boot-3

open swagger

http://localhost:8870/admin/swagger-ui/index.html#/


https://stackoverflow.com/questions/56861256/gradle-build-failed-main-class-name-has-not-been-configured-and-it-could-not-be

