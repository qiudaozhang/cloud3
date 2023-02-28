package top.daozhang.account

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication(scanBasePackages = ["top.daozhang"])
@EnableDiscoveryClient
@EnableDubbo
@MapperScan(basePackages = ["top.daozhang.account.mapper"])
class AccountApp {
}

fun main(args: Array<String>) {
    SpringApplication.run(AccountApp::class.java, *args)
}