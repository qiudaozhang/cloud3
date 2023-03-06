package top.daozhang.config

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import top.daozhang.common.R

/**
 * 全局异常处理
 */
@RestControllerAdvice
class GlobalExceptionHandler {



    @ExceptionHandler(value = [Exception::class])
    fun exception(e:Exception):R<Any>{
        return R.fail("公共异常")
    }
}