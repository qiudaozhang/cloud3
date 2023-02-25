package top.daozhang.common

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime


// 时间模型
@Schema(title = "时间模型")
open class TimeModel {
    @Schema(title = "创建时间")
    var created:LocalDateTime?=null
    @Schema(title = "更新时间")
    var updated:LocalDateTime?=null
}