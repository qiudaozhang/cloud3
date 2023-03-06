package top.daozhang.admin.controller

import cn.hutool.core.date.DateUtil
import cn.hutool.crypto.SecureUtil
import cn.hutool.crypto.asymmetric.KeyType
import cn.hutool.crypto.symmetric.SymmetricAlgorithm
import io.seata.spring.annotation.GlobalTransactional
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.apache.commons.lang3.RandomStringUtils
import org.apache.dubbo.config.annotation.DubboReference
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import system.service.DictService
import top.daozhang.account.entity.Auth
import top.daozhang.account.service.AuthService
import top.daozhang.common.R
import top.daozhang.config.RsaConfig

@RestController
@RequestMapping(value = ["api/test"])
@Tag(name = "全局事务测试")
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