package top.daozhang.account.entity

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.v3.oas.annotations.media.Schema
import top.daozhang.common.IdModel
import java.io.Serial


/**
 * @author dao
 * @since 2023-2-28
 *
 * 资源模型，资源的上下级关系不要太深，太深用关系型数据库维护性能不太好
 */
@ApiModel(value = "资源")
open class Resource : IdModel() {

    @ApiModelProperty(value = "名称")
    var name: String? = null

    @ApiModelProperty(value = "资源类型")
    var type: String? = null

    @ApiModelProperty(value = "资源图标，如果是前端的一些资源可能需要这个")
    var icon: String? = null

    @ApiModelProperty(value = "展示名称")
    var showName: String? = null

    @ApiModelProperty(value = "父资源id")
    var pid: Long? = null

    // 可以限制这个最大的深度
    @ApiModelProperty(value = "级别，深度")
    var level: Int? = null

    companion object {
        @Serial
        private const val serialVersionUID: Long = -1208564661126489964L

    }

    override fun toString(): String {
        return "Resource(name=$name, type=$type, icon=$icon, showName=$showName, pid=$pid, level=$level)"
    }


}