
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-seata")
    api(project(":cloud3-api:account-api"))
    implementation("com.mysql:mysql-connector-j")
    implementation("com.baomidou:mybatis-plus-boot-starter")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")
    implementation("org.apache.dubbo:dubbo")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("com.alibaba.nacos:nacos-client")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-loadbalancer")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}