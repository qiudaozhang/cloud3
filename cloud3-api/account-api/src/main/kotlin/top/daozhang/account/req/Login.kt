package top.daozhang.account.req

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serial
import java.io.Serializable

@Schema(description = "登录请求")
class Login:Serializable {
    @Schema(description = "用户名", example = "dao")
    var account: String? = null

    @Schema(description = "密码", example = "123456")
    var password: String? = null
    companion object {
        @Serial
        private const val serialVersionUID: Long = 441405041989940055L

    }
}