package top.daozhang.admin.config


import cn.hutool.core.collection.CollectionUtil
import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket


/*
参考代码
https://gitee.com/xiaoym/swagger-bootstrap-ui-demo/blob/master/knife4j-spring-boot3-demo/src/main/java/com/github/xiaoymin/boot3/config/SwaggerConfig.java

 */


/**
 * swagger 配置
 */
@Configuration
class SwaggerConfig {
    fun defaultApi(): Docket? {
        //.extensions(openApiExtensionResolver.buildExtensions("3.默认接口"));
        //.extensions(openApiExtensionResolver.buildSettingExtensions())
        //.securityContexts(CollectionUtil.newArrayList(securityContext()))
        //.securitySchemes(CollectionUtil.newArrayList(apiKey()));
        // .securitySchemes(securitySchemes());
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .groupName("3.默认接口")
            .select()
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation::class.java))
            .paths(PathSelectors.any())
            .build().securityContexts(CollectionUtil.newArrayList(securityContext()))
            .securitySchemes(CollectionUtil.newArrayList<SecurityScheme>(apiKey()))
    }

    private fun securitySchemes(): List<SecurityScheme>? {
        val apiKeyList: MutableList<SecurityScheme> = ArrayList()
        apiKeyList.add(HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build())
        return apiKeyList
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfoBuilder() //.title("swagger-bootstrap-ui-demo RESTful APIs")
            .description("# swagger-bootstrap-ui-demo RESTful APIs")
            .termsOfServiceUrl("http://www.xx.com/") //.contact("xx@qq.com")
            .version("")
            .build()
    }

    //
    private fun apiKey(): ApiKey? {
        return ApiKey("BearerToken", "Authorization", "header")
    }

    //    private ApiKey apiKey1() {
    //        return new ApiKey("BearerToken1", "Authorization-x", "header");
    //    }
    //
    private fun securityContext(): SecurityContext? {
        return SecurityContext.builder()
            .securityReferences(defaultAuth()) //.forPaths(PathSelectors.regex(".*?208.*$"))
            .build()
    }

    //    private SecurityContext securityContext1() {
    //        return SecurityContext.builder()
    //                .securityReferences(defaultAuth1())
    //                .forPaths(PathSelectors.regex("/.*"))
    //                .build();
    //    }
    //
    fun defaultAuth(): List<SecurityReference?>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes: Array<AuthorizationScope?> = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return CollectionUtil.newArrayList(SecurityReference("BearerToken", authorizationScopes))
    }

}