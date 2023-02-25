package top.daozhang.admin
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AdminApp

fun main(args: Array<String>) {
    SpringApplication.run(AdminApp::class.java,*args)
}