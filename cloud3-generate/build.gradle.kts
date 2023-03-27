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

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}