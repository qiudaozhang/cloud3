package top.daozhang.admin.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import top.daozhang.common.TimeModel
import java.time.LocalDateTime

@RestController
@Tag(name = "时间")
class TimeController {

    @GetMapping
    @Operation(description = "临时测试", method = "临时测试")
    fun getAll():List<TimeModel>{
        val t = TimeModel()
        val now = LocalDateTime.now()
        t.created = now
        t.updated = now
        return listOf(t)
    }
    @PostMapping
    @Operation( description = "新增")
    fun create(timeModel: TimeModel):Unit{
    }
}