package top.daozhang.admin.controller

import com.github.xiaoymin.knife4j.annotations.ApiSort
import com.github.xiaoymin.knife4j.annotations.ApiSupport
import com.github.yitter.idgen.YitIdHelper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.dubbo.config.annotation.DubboReference
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.daozhang.account.entity.Resource
import top.daozhang.account.service.ResourceService
import top.daozhang.account.vo.ResourceVo
import top.daozhang.common.R

@RestController
@Tag(name = "资源")
@RequestMapping(value = ["api/resource"])
@ApiSupport(order = 2) // spring boot3 似乎无法生效
class ResourceController {

    @DubboReference
    lateinit var resourceService: ResourceService

    @Operation(summary = "创建", description = "创建")
    @PostMapping
    fun create(@RequestBody resource: Resource): R<Any> {
        resourceService.createOne(resource)
        return R.suc()
    }

    @Operation(summary = "查询单个", description = "查询单个资源数据，包含其子数据")
    @GetMapping(value = ["{id}"])
    fun get(@PathVariable("id") id: Long):R<ResourceVo?> {
        val one = resourceService.findOne(id)
        return R.data(one)
    }

}