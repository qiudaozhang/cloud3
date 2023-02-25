package top.daozhang.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import top.daozhang.common.TimeModel
import java.time.LocalDateTime

@RestController
@Tag(name = "时间")
class TimeController {

    @GetMapping
    @Operation(description = "临时测试", tags = ["查询所有时间"])
    fun getAll():List<TimeModel>{

        val t = TimeModel()
        val now = LocalDateTime.now()
        t.created = now
        t.updated = now
        return listOf(t)
    }
}