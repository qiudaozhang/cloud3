package top.daozhang.common

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serial
import java.io.Serializable
import java.time.LocalDateTime

/**
 * @author dao
 * @since 2023-2-26
 * 时间模型
 */
@Schema(title = "时间模型")
open class TimeModel:Serializable{
    @Schema(description = "创建时间，前端不需要提交", required = false)
    var created:LocalDateTime?=null
    @Schema(description = "更新时间，前端不需要提交", required = false)
    var updated:LocalDateTime?=null


    open fun initData(){
        val now = LocalDateTime.now()
        created = now
        updated = now
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = -3911420510128981610L
    }
}