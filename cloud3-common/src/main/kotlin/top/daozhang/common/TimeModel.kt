package top.daozhang.common

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serial
import java.io.Serializable
import java.time.LocalDateTime


// 时间模型
@Schema(title = "时间模型")
open class TimeModel:Serializable{
    @Schema(title = "创建时间")
    var created:LocalDateTime?=null
    @Schema(title = "更新时间")
    var updated:LocalDateTime?=null

    companion object {
        @Serial
        private const val serialVersionUID: Long = -3911420510128981610L
    }
}