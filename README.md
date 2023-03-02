
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


## 参考资料


https://stackoverflow.com/questions/74614369/how-to-run-swagger-3-on-spring-boot-3

open swagger

http://localhost:8870/admin/swagger-ui/index.html#/


https://stackoverflow.com/questions/56861256/gradle-build-failed-main-class-name-has-not-been-configured-and-it-could-not-be

