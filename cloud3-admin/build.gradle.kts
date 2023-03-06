import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    // copy from https://spring.io/guides/tutorials/spring-boot-kotlin/
    id("java")
    id("org.springframework.boot") version "3.0.3"
    id("io.spring.dependency-management") version "1.1.0"
//    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
//    id("org.jetbrains.kotlin.plugin.spring") version "1.7.22"
    id("org.jetbrains.kotlin.plugin.spring") version "1.8.0"

//    kotlin("plugin.spring") version "1.8.0"
//    kotlin("plugin.jpa") version "1.8.0"
}

group = "top.daozhang"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-seata")

    // 需要使用子模块的具体依赖用下面这
    api(project(":cloud3-common"))
    api(project(":cloud3-api:account-api"))
    api(project(":cloud3-api:system-api"))
    // 雪花id
    implementation("com.github.yitter:yitter-idgenerator:1.0.6")
    implementation("org.apache.commons:commons-pool2:2.11.1")
    implementation("cn.dev33:sa-token-dao-redis-jackson:1.34.0")

    // sa token
    implementation("cn.dev33:sa-token-spring-boot-starter")
    implementation("cn.dev33:sa-token-jwt")
    implementation("cn.dev33:sa-token-alone-redis")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    // 微服务通用依赖
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("org.apache.dubbo:dubbo-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("com.alibaba.nacos:nacos-client")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-loadbalancer")
//    api 支持
    implementation("com.github.xiaoymin:knife4j-spring-boot-starter:3.0.3")
//    implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.0.0")
    // 如果不需要引入module里面的具体依赖 需要使用子模块的具体依赖用下面这个
//    implementation(project(":cloud3-common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")


}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

