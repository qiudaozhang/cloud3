package top.daozhang.account.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import top.daozhang.common.IdModel
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
@ApiModel(description = "Auth对象")
@TableName(value = "auth")
@JsonIgnoreProperties(value = ["password"])
class Auth : IdModel(), Serializable {


    @ApiModelProperty(value = "用户名")
    var username: String? = null

    @ApiModelProperty(value = "密码")
    var password: String? = null

    @ApiModelProperty(value = "手机号")
    var phone: String? = null

    @ApiModelProperty(value = "邮箱")
    var email: String? = null

    companion object {
        @Serial
        private const val serialVersionUID: Long = 8920169536918088484L

    }


}
