package top.daozhang.common

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "服务器统一返回数据模型")
class R<T> {

    @Schema(description = "code")
    var code: String? = null
    @Schema(description = "消息")
    var msg: String? = null
    @Schema(description = "业务数据")
    var data: T? = null
    companion object {

        /**
         * 仅用于操作的成功，并不需要携带具体业务数据回去
         */
        fun suc(): R<Any> {
            val r = R<Any>()
            r.msg = "success"
            r.code = "0"
            return r
        }
        fun fail(msg:String): R<Any> {
            val r = R<Any>()
            r.msg = msg
            r.code = "1"
            return r
        }

        fun<T> data(data:T):R<T>{
            val r = R<T>()
            r.msg = "success"
            r.code = "0"
            r.data = data
            return r
        }

    }
}