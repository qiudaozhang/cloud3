package top.daozhang.common

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.github.yitter.idgen.YitIdHelper
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.annotation.Resource
import java.io.Serial

open class IdModel:DeleteModel() {
    @Schema(description = "主键,创建操作不需要提交，更新操作需要提交")
    @TableId(type = IdType.INPUT)
    var id: Long? = null

//    @Resource

    override fun initData() {
        super.initData()
        id = YitIdHelper.nextId()
    }

    companion object {
        @Serial
        private const val serialVersionUID: Long = -1527804715635006858L

    }

}