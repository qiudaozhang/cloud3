//plugins {
//    id("java")
//}
plugins {
    id("org.jetbrains.kotlin.plugin.lombok") version "1.8.10"
//  id "org.jetbrains.kotlin.plugin.allopen" version "1.8.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.10"

}
repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.spring.io/release")
    }
    maven {
        url = uri("https://repository.jboss.org/maven2")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.8.10")

    api("com.github.xiaoymin:knife4j-gateway-spring-boot-starter:4.1.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.apache.dubbo:dubbo")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("com.alibaba.nacos:nacos-client")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-loadbalancer")
}

//tasks.getByName<Test>("test") {
//    useJUnitPlatform()
//}
