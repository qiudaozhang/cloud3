plugins {
    id("java")
}

group = "top.daozhang"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:2022.0.0.0-RC1")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:2022.0.0.0-RC1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}