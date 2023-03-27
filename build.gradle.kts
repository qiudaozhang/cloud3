val kotlinVersion = "1.8.10"

plugins {
    // 依赖管理插件
    val kotlinVersion = "1.8.10"
    val springBootVersion = "3.0.4"
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version "1.1.0"
//    kotlin("jvm") version "1.8.20"
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.spring") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.10"

//    大量Java项目可能依赖这个，所以需要添加一下
//    kotlin("plugin.lombok") version "1.8.10"
}


group = "top.daozhang"
version = "0.0.1"



subprojects {
    val mybatisPlusVersion = "3.5.3.1"
    val dubboVersion = "3.2.0-beta.6"
    val saTokenVersion = "1.34.0"
    val alibabaVersion = "2022.0.0.0-RC1"

    apply(plugin = "java")
//    有些Java的项目是依赖lombok完成的
    apply(plugin = "org.jetbrains.kotlin.jvm")

    configurations.all {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
    repositories {
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/spring/")
        }
        mavenLocal()
        mavenCentral()
    }

    apply {
        plugin("kotlin")
        plugin("org.jetbrains.kotlin.plugin.allopen")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.0")
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2022.0.0.0-RC1")
        }
        dependencies {
            dependency("cn.dev33:sa-token-spring-boot3-starter:${saTokenVersion}")
            dependency("cn.dev33:sa-token-jwt:${saTokenVersion}")
            dependency("cn.dev33:sa-token-alone-redis:${saTokenVersion}")
            dependency("com.alibaba.cloud:spring-cloud-starter-alibaba-seata:${alibabaVersion}")
            dependency("org.freemarker:freemarker:2.3.32")
            dependency("com.baomidou:mybatis-plus-core:${mybatisPlusVersion}")
            dependency("com.baomidou:mybatis-plus-generator:${mybatisPlusVersion}")
            dependency("com.baomidou:mybatis-plus-extension:${mybatisPlusVersion}")
            dependency("com.baomidou:mybatis-plus-boot-starter:${mybatisPlusVersion}")
            dependency("org.apache.dubbo:dubbo-spring-boot-starter:${dubboVersion}")
            dependency("org.apache.dubbo:dubbo:${dubboVersion}")
            dependency("com.mysql:mysql-connector-j:8.0.32")
            dependency("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:${alibabaVersion}")
            dependency("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:${alibabaVersion}")
            dependency("org.springframework.cloud:spring-cloud-starter-gateway:4.0.1")
            dependency("org.springframework.cloud:spring-cloud-starter-bootstrap:4.0.1")
            dependency("org.springframework.cloud:spring-cloud-loadbalancer:4.0.1")
            dependency("com.alibaba.nacos:nacos-client:2.1.1")
        }
    }


    dependencies {
//        implementation("org.jetbrains.kotlin:@ApiOperation(value =:${kotlinVersion}")
//        implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
//        implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    }
}

// 所有的项目都生效
allprojects {
    group = "top.daozhang"
    version = "0.0.1"
    apply {
        plugin("org.jetbrains.kotlin.plugin.allopen")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }
}


repositories {
    maven {
        setUrl("https://maven.aliyun.com/repository/public/")
    }
    maven {
        setUrl("https://maven.aliyun.com/repository/spring/")
    }
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

}

// 如果没有启动程序的，指定为FALSE
tasks.bootJar {
    enabled = false
}


kotlin {
    jvmToolchain(17)
}