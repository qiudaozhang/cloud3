
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

## 参考资料


https://stackoverflow.com/questions/74614369/how-to-run-swagger-3-on-spring-boot-3

open swagger

http://localhost:8870/admin/swagger-ui/index.html#/


https://stackoverflow.com/questions/56861256/gradle-build-failed-main-class-name-has-not-been-configured-and-it-could-not-be

