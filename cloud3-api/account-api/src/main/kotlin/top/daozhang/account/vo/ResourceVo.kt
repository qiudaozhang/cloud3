package top.daozhang.account.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.swagger.v3.oas.annotations.media.Schema
import top.daozhang.account.entity.Resource
import java.io.Serial

@Schema(description = "资源展示模型")
@JsonIgnoreProperties(value = ["mountFinish"])
class ResourceVo : Resource() {

    @Schema(description = "距离")
    var distance: Int? = null

    @Schema(description = "子资源")
    var children: List<ResourceVo>? = null

    @Schema(description = "挂载完毕的标志")
    var mountFinish: Boolean? = false

    var parent:ResourceVo?=null
    companion object {
        @Serial
        private const val serialVersionUID: Long = 4642660173102711312L
    }

    override fun toString(): String {
        return "ResourceVo(distance=$distance) ${super.toString()}"
    }


}