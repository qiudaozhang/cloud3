package top.daozhang.admin.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.dev33.satoken.stp.SaLoginConfig
import cn.dev33.satoken.stp.StpUtil
import com.github.yitter.idgen.YitIdHelper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource
import org.apache.commons.lang3.RandomStringUtils
import org.apache.dubbo.config.annotation.DubboReference
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.daozhang.account.entity.Auth
import top.daozhang.account.req.Login
import top.daozhang.account.req.Register
import top.daozhang.account.service.AuthService
import top.daozhang.admin.config.StpAdminUtil
import top.daozhang.common.R
import top.daozhang.util.NonNullBeanUtil

@RestController
@RequestMapping(value = ["api/auth"])
@Tag(name = "认证")
class AuthController {

    @DubboReference
    lateinit var authService: AuthService

    @Resource
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Operation(summary = "注册")
    @PostMapping(value = ["register"])
    fun register(@RequestBody register: Register): R<Any> {

        val auth = Auth()

        val has = authService.hasAccount(register.username, register.phone, register.email)
        if (has) {
            return R.fail("注册失败，账号已被使用！")
        }
        NonNullBeanUtil.copyProperties(register, auth)
        auth.id = YitIdHelper.nextId()
        auth.initData()
        auth.password = bCryptPasswordEncoder.encode(auth.password)
        authService.save(auth)
        return R.suc()
    }

    @Operation(summary = "登录")
    @PostMapping(value = ["login"])
    fun login(@RequestBody login: Login): R<Any> {

        val account = login.account
        val auth = authService.findAccount(account, account, account) ?: return R.fail("账号或密码错误！")
        // 密码比对，如果通过则ok

        if (bCryptPasswordEncoder.matches(login.password, auth.password)) {
            // 登录成功
            StpAdminUtil.login(auth.id)
            return R.data(StpAdminUtil.getTokenValue())
        }
        return R.fail("账号或密码错误！")
    }
    @Operation(summary = "获取个人信息")
    @PostMapping(value = ["info"])
    @SaCheckLogin(type = StpAdminUtil.TYPE)
    fun info(): R<Auth> {
        val uid = StpAdminUtil.uid()
        uid?.let {
            val auth = authService.getById(uid)
            return R.data(auth)
        }
        throw RuntimeException("获取失败")
    }

}