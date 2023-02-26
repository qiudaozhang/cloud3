val kotlinVersion = "1.8.0"

plugins {
    // 依赖管理插件
    val kotlinVersion = "1.8.0"
    val springBootVersion = "3.0.3"
    id("org.springframework.boot") version  springBootVersion
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
}


group = "top.daozhang"
version = "0.0.1"



subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    configurations.all{
        exclude("org.springframework.boot","spring-boot-starter-logging")
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

    dependencyManagement{
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.0")
            mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2022.0.0.0-RC1")
        }
        dependencies {
            dependency("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2022.0.0.0-RC1")
            dependency("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:2022.0.0.0-RC1")
            dependency("org.springframework.cloud:spring-cloud-starter-gateway:4.0.1")
            dependency("org.springframework.cloud:spring-cloud-starter-bootstrap:4.0.1")
            dependency("org.springframework.cloud:spring-cloud-loadbalancer:4.0.1")
            dependency("org.apache.dubbo:dubbo:3.2.0-beta.5")
            dependency("com.alibaba.nacos:nacos-client:2.1.1")
        }
    }


    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-allopen:${kotlinVersion}")
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
tasks.bootJar  {
     enabled=false
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}