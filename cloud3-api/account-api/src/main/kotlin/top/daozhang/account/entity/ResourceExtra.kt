package top.daozhang.account.entity

import com.baomidou.mybatisplus.annotation.TableName
import io.swagger.v3.oas.annotations.media.Schema
import top.daozhang.common.PureId
import java.io.Serial

@Schema(description = "资源额外补充信息")
@TableName(value = "resource_extra")
class ResourceExtra :PureId(){

    @Schema(description = "根资源id")
    var rootId:Long?=null
    @Schema(description = "父资源id")
    var pid:Long?=null
    @Schema(description = "资源id")
    var rid:Long?=null
    @Schema(description = "距离")
    var distance:Int?=null

    companion object {
        @Serial
        private const val serialVersionUID: Long = 4196464791944419812L
    }

}