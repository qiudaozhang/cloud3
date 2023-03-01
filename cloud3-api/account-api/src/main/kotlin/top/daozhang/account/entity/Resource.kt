package top.daozhang.account.entity

import io.swagger.v3.oas.annotations.media.Schema
import top.daozhang.common.IdModel
import java.io.Serial


/**
 * @author dao
 * @since 2023-2-28
 *
 * 资源模型，资源的上下级关系不要太深，太深用关系型数据库维护性能不太好
 */
@Schema(description = "资源")
open class Resource:IdModel() {

    @Schema(description = "名称")
    var name:String?=null
    @Schema(description = "资源类型")
    var type:String?=null
    @Schema(description = "资源图标，如果是前端的一些资源可能需要这个")
    var icon:String?=null
    @Schema(description = "展示名称")
    var showName:String?=null
    @Schema(description = "父资源id")
    var pid:Long?=null
    // 可以限制这个最大的深度
    @Schema(description = "级别，深度")
    var level:Int?=null

    companion object {
        @Serial
        private const val serialVersionUID: Long = -1208564661126489964L

    }

    override fun toString(): String {
        return "Resource(name=$name, type=$type, icon=$icon, showName=$showName, pid=$pid, level=$level)"
    }


}