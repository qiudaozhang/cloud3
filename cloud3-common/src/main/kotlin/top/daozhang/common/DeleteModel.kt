package top.daozhang.common

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serial

open class DeleteModel:TimeModel() {

    @Schema(description = "逻辑删除状态 0 未删除 1 已删除")
    var deleted:Int?=null


    override fun initData() {
        super.initData()
        this.deleted = 0
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = -5926820217314301855L

    }
}