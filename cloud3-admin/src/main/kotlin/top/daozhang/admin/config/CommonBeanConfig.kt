package top.daozhang.admin.config

import cn.dev33.satoken.jwt.StpLogicJwtForSimple
import cn.dev33.satoken.stp.StpLogic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class CommonBeanConfig {
    @Bean
    fun bcryptBean(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun getStpLogicJwt():StpLogic {
        return StpLogicJwtForSimple()
    }
}