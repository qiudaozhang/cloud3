//plugins {
//    id("java")
//    kotlin("jvm") version "1.8.0"
//}
// 定义一个子模块
group = "top.daozhang"
version = "0.0.1"

//repositories {
//    mavenCentral()
//}

dependencies {
    api("io.swagger.core.v3:swagger-annotations:2.2.8")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.bootJar  {
    enabled=false
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}