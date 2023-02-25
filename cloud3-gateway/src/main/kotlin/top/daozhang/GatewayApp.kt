package top.daozhang

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


//@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableDiscoveryClient
@EnableDubbo
@SpringBootApplication
class GatewayApp {
}

fun main(args: Array<String>) {
    SpringApplication.run(GatewayApp::class.java,*args)
}