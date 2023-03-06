package top.daozhang.admin.controller

import cn.hutool.core.date.DateUtil
import cn.hutool.crypto.SecureUtil
import cn.hutool.crypto.asymmetric.KeyType
import cn.hutool.crypto.symmetric.SymmetricAlgorithm
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.daozhang.common.R
import top.daozhang.config.RsaConfig
import javax.annotation.Resource

@RestController
@RequestMapping(value = ["api/tool"])
@Api(tags =["工具"])
class ToolController {


    @Resource
    lateinit var rsaConfig: RsaConfig

    @Resource
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder


    @PostMapping(value = ["randomPwd"])
      @ApiOperation(value = "随机密码", notes = "生成一个随机密码")
    @Parameters(
        value = [
            Parameter(name = "len", `in` = ParameterIn.QUERY, description = "密码长度")
        ]
    )
    fun randomPassword(len: Long): R<String> {
        val r = RandomStringUtils.randomAlphanumeric(len.toInt())
        return R.data(r)
    }


    @PostMapping(value = ["aes/crypt"])
      @ApiOperation(value = "aes加密", notes = "根据算法计算加密的值")
    fun crypt(key:String, value: String): R<String> {
        // 指定一个固定key
        val aes = SecureUtil.aes(key.toByteArray())
        val r = aes.encryptHex(value)
        return R.data(r)
    }

    @PostMapping(value = ["aes/generate"])
      @ApiOperation(value = "生成一个aes key", notes = "展示只提供128位")
    fun generateAesKey(): R<String> {
        val byteArray = SecureUtil.generateKey(SymmetricAlgorithm.AES.value, 128).encoded
        val baseKey: String = java.util.Base64.getEncoder().encodeToString(byteArray)
        return R.data(baseKey)
    }

    @PostMapping(value = ["rsa/crypt"])
      @ApiOperation(value = "rsa加密", notes = "根据算法计算加密的值")
    fun cryptRsa(value: String): R<String> {
        val rsa = SecureUtil.rsa(rsaConfig.priKey, rsaConfig.pubKey)
        return R.data(rsa.encryptBase64(value, KeyType.PublicKey))
    }


    @PostMapping(value = ["bcrypt/crypt"])
      @ApiOperation(value = "bcrypt加密", notes = "根据算法计算加密的值")
    fun bcrypt(value: String): R<String> {
        return R.data(bCryptPasswordEncoder.encode(value))
    }


    @PostMapping(value = ["rsa/decrpyt"])
      @ApiOperation(value = "rsa解密", notes = "根据算法计算加密的值")
    fun decryptRsa(value: String): R<Any> {
        // 传入base 64的内容
        val rsa = SecureUtil.rsa(rsaConfig.priKey, rsaConfig.pubKey)
        return R.data(rsa.decryptStr(value, KeyType.PrivateKey))
    }


    @GetMapping("/ts/second")
      @ApiOperation(value = "时间戳（秒）", notes = "获取当前的时间戳秒")
    fun tsSecond(): R<Long> {
        return R.data(DateUtil.currentSeconds())
    }


//    @PostMapping(value = ["aes/crypt"])
//      @ApiOperation(value = "aes加密", description = "根据算法计算加密的值")
//    fun crypt(key: String, value: String): R<String> {
//
//        val byteArray = SecureUtil.generateKey(SymmetricAlgorithm.AES.value,128).encoded
//        // 这种才能看明白，比如：4U0Y4oYispKnvodsVj3dXQ==
//        val baseKey: String = Base64.getEncoder().encodeToString(byteArray)
//        val aes = SecureUtil.aes(byteArray)
//        val r = aes.encryptHex(value)
//        return R.data(r)
//    }


}