plugins {
    id("java")
}


repositories {
    mavenCentral()
}

dependencies {
    api(project(":cloud3-common"))
    implementation("org.springframework.boot:spring-boot-starter-json")
    api("com.baomidou:mybatis-plus-core")
    api("com.baomidou:mybatis-plus-extension")
}

tasks.bootJar  {
    enabled=false
}


