plugins {
    id("java")
}

group = "top.daozhang"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":cloud3-common"))
    implementation("org.springframework.boot:spring-boot-starter-json")
    api("com.baomidou:mybatis-plus-core")
    api("com.baomidou:mybatis-plus-extension")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}