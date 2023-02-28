package top.daozhang.account.req

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serial
import java.io.Serializable

@Schema(description = "注册请求")
class Register:Serializable {
    @Schema(description = "用户名", example = "dao")
    var username: String? = null

    @Schema(description = "密码", example = "123456")
    var password: String? = null

    @Schema(description = "手机号", example = "13912341234")
    var phone: String? = null

    @Schema(description = "邮箱", example = "13912341234@gmail.com")
    var email: String? = null
    companion object {
        @Serial
        private const val serialVersionUID: Long = 441405041989940055L

    }
}