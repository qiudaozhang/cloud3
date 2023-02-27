package top.daozhang.admin.config

import cn.hutool.core.util.RandomUtil
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
参考代码
https://gitee.com/xiaoym/swagger-bootstrap-ui-demo/blob/master/knife4j-spring-boot3-demo/src/main/java/com/github/xiaoymin/boot3/config/SwaggerConfig.java

 */


/**
 * swagger 配置
 */
@Configuration
class SwaggerConfig {
    @Bean
    fun orderGlobalOpenApiCustomizer(): GlobalOpenApiCustomizer? {
        return GlobalOpenApiCustomizer { openApi: OpenAPI ->
            if (openApi.tags != null) {
                openApi.tags.forEach {
                    val map = mutableMapOf<String,Any>()
                    map["x-order"] = RandomUtil.randomInt(0,100)
                    it.extensions = map
                }

            }
            if (openApi.paths != null) {
                openApi.addExtension("x-test123", "333")
                openApi.paths.addExtension("x-abb", RandomUtil.randomInt(1, 100))
            }
        }
    }

    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI()
            .info(
                Info()
                    .title("cloud3")
                    .version("1.0")
                    .description("api docs")
                    .termsOfService("xxxx.com")
                    .license(
                        License().name("Apache 2.0")
                            .url("xxxx.com")
                    )
            )
    }

}