package top.daozhang.account.vo

import io.swagger.v3.oas.annotations.media.Schema
import top.daozhang.account.entity.Resource
import java.io.Serial

@Schema(description = "资源展示模型")
class ResourceVo:Resource() {

    @Schema(description = "子资源")
    var children:List<Resource>?=null

    companion object {
        @Serial
        private const val serialVersionUID: Long = 4642660173102711312L
    }
}