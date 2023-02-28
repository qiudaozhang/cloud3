//plugins {
//    id("java")
//}


repositories {
    mavenCentral()
}

dependencies {
    implementation("com.baomidou:mybatis-plus-generator")
    implementation("com.baomidou:mybatis-plus-core")
    implementation("com.mysql:mysql-connector-j")
    implementation("org.slf4j:slf4j-api:2.0.6")
    implementation("org.freemarker:freemarker")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}