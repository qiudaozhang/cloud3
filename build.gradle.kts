plugins {
    id("java")
//    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.kotlin.jvm") version "1.8.0"

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


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}