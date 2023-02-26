package top.daozhang.consts

import java.time.format.DateTimeFormatter

interface TimeConst {
    companion object {
        const val FORMAT_STR = "yyyy-MM-dd HH:mm:ss"
        const val FORMAT_TIME_STR = "HH:mm:ss"
        const val FORMAT_DATE_STR = "yyyy-MM-dd"
        val FORMAT = DateTimeFormatter.ofPattern(FORMAT_STR)
        val FORMAT_TIME = DateTimeFormatter.ofPattern(FORMAT_TIME_STR)
        val FORMAT_DATE = DateTimeFormatter.ofPattern(FORMAT_DATE_STR)
    }
//    val  FORMAT_STR = "yyyy-MM-dd HH:mm:ss"
//    var FORMAT_TIME_STR = "HH:mm:ss"
//    var
//    var
//    var
//    var
//
//
//    var ONE_DAY_SECONDS = 86400L
//
//    var TEN_MINUTE = 600L
}