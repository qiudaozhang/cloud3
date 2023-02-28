package top.daozhang.admin.config

import cn.dev33.satoken.SaManager
import cn.dev33.satoken.jwt.StpLogicJwtForSimple
import cn.dev33.satoken.stp.StpLogic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class CommonBeanConfig {
    @Bean
    fun bcryptBean(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // 这里和下面结合起来才可以试用
    @Bean
    fun getStpLogic(): StpLogic? {
        return StpLogicJwtForSimple()
    }

    @Autowired
    fun setUserStpLogic(){
        // 自定义的一套 类型生效
        StpAdminUtil.stpLogic =StpLogicJwtForSimple(StpAdminUtil.TYPE)
        SaManager.putStpLogic(StpAdminUtil.stpLogic)
    }


}