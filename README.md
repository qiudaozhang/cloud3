
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


## Resource设计与处理


| 父id | 子id | 距离 |
| ---- | ---- | ---- |
| 1    | 2    | 1    |
| 2    | 3    | 1    |
| 1    | 3    | 1    |


针对删除资源1，由于所有和1有关联的数据，都能找到，此外还需要清理所有的子资源，所以这里也需要一个递归



## 参考资料


https://stackoverflow.com/questions/74614369/how-to-run-swagger-3-on-spring-boot-3

open swagger

http://localhost:8870/admin/swagger-ui/index.html#/


https://stackoverflow.com/questions/56861256/gradle-build-failed-main-class-name-has-not-been-configured-and-it-could-not-be

