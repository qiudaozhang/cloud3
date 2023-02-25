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
    // 需要使用子模块的具体依赖用下面这
    api(project(":cloud3-common"))
//    implementation("org.springdoc:springdoc-openapi-ui:1.6.14")
//    api("io.springfox:springfox-swagger-ui:3.0.0")
//    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    // 如果不需要引入module里面的具体依赖 需要使用子模块的具体依赖用下面这个
//    implementation(project(":cloud3-common"))
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

