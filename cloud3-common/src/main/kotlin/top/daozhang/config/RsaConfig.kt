package top.daozhang.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "rsa")
@ConditionalOnProperty(name = ["rsa.enable"], havingValue = "true")
class RsaConfig {

    var pubKey:String?=null
    var priKey:String?=null
    var enable: Boolean?=null
}