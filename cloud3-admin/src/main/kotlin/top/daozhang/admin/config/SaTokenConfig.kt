package top.daozhang.admin.config

import cn.dev33.satoken.interceptor.SaInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SaTokenConfig:WebMvcConfigurer {

    // 注解鉴权生效
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(SaInterceptor()).addPathPatterns("/**")
    }
}