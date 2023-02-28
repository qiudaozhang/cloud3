package top.daozhang.consts

import java.time.format.DateTimeFormatter

/**
 * @author dao
 * @since 2023-2-27
 * 时间常量
 */
interface TimeConst {
    companion object {
        private const val FORMAT_STR = "yyyy-MM-dd HH:mm:ss"
        private const val FORMAT_TIME_STR = "HH:mm:ss"
        private const val FORMAT_DATE_STR = "yyyy-MM-dd"
        val FORMAT: DateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_STR)
        val FORMAT_TIME: DateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_TIME_STR)
        val FORMAT_DATE: DateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE_STR)
    }
}