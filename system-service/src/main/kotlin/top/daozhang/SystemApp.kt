package top.daozhang

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication(scanBasePackages = ["top.daozhang"])
@EnableDiscoveryClient
@EnableDubbo
@MapperScan(basePackages = ["top.daozhang.system.mapper"])
@EnableAspectJAutoProxy(proxyTargetClass = true)
class SystemApp {
}

fun main(args: Array<String>) {
    SpringApplication.run(SystemApp::class.java, *args)
}