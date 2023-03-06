package top.daozhang.admin
import com.github.yitter.contract.IdGeneratorOptions
import com.github.yitter.idgen.YitIdHelper
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@SpringBootApplication(scanBasePackages = ["top.daozhang"])
@EnableDiscoveryClient
@EnableDubbo
/*
--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.nio=ALL-UNNAMED --add-opens java.base/sun.nio.ch=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens=java.base/java.math=ALL-UNNAMED
 */

class AdminApp

fun main(args: Array<String>) {
    val options = IdGeneratorOptions(1)
    YitIdHelper.setIdGenerator(options)
    SpringApplication.run(AdminApp::class.java,*args)
}