package top.daozhang.admin
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["top.daozhang"])
@EnableDiscoveryClient
@EnableDubbo
class AdminApp

fun main(args: Array<String>) {
    SpringApplication.run(AdminApp::class.java,*args)
}