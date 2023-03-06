package top.daozhang.admin.controller

import io.seata.spring.annotation.GlobalTransactional
import io.swagger.annotations.Api
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.dubbo.config.annotation.DubboReference
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import system.service.DictService
import top.daozhang.account.entity.Auth
import top.daozhang.account.service.AuthService

@RestController
@RequestMapping(value = ["api/test"])
@Api(tags =["全局事务测试"])
class GlobalTxTestController {


    @DubboReference
    lateinit var authService: AuthService

    @DubboReference
    lateinit var dictService: DictService

    @PostMapping("tx")
    // 增加注解即可完成分布式事务，进行undo
    @GlobalTransactional(rollbackFor = [Exception::class])
    fun globalTx(){
        val auth = Auth()
        auth.initData()
        auth.username="test"
        authService.save(auth)
        dictService.txSave()
    }




}