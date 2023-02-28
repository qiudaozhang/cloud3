package top.daozhang.account.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema
import top.daozhang.common.DeleteModel
import java.io.Serial
import java.io.Serializable

/**
 * <p>
 *
 * </p>
 *
 * @author 邱道长
 * @since 2023-02-27
 */
@Schema(description = "Auth对象" )
@TableName(value = "auth")
@JsonIgnoreProperties(value = ["password"])
class Auth : DeleteModel(), Serializable {


    @Schema(description = "主键")
    @TableId(type = IdType.INPUT)
    var id: Long? = null

    @Schema(description = "用户名")
    var username: String? = null

    @Schema(description = "密码")
    var password: String? = null

    @Schema(description = "手机号")
    var phone: String? = null

    @Schema(description = "邮箱")
    var email: String? = null

    companion object {
        @Serial
        private const val serialVersionUID: Long = 8920169536918088484L

    }


}
