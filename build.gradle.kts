plugins {
    id("java")
//    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
    id("io.spring.dependency-management") version "1.1.0"

}

group = "top.daozhang"
version = "0.0.1"



subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }


}

// 所有的项目都生效
allprojects {


    repositories {
        maven(url = "https://maven.aliyun.com/repository/public/")
        mavenLocal()
        mavenCentral()
    }
}



dependencyManagement{
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.0")
    }
}


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
//    import("org.springframework.cloud:spring-cloud-dependencies:2022.0.0")


}


tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}