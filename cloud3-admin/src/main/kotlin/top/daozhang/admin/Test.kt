//package top.daozhang.admin
//
//import cn.hutool.crypto.SecureUtil
//import cn.hutool.crypto.symmetric.SymmetricAlgorithm
//import java.util.*
//
//object Test {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val byteArray = SecureUtil.generateKey(SymmetricAlgorithm.AES.value,128).encoded
//        val baseKey: String = Base64.getEncoder().encodeToString(byteArray)
//        println(baseKey)
//
//        val aes = SecureUtil.aes(byteArray)
//        val r = aes.encryptHex("123456")
//
//        println(r)
//        val secret = aes.encryptBase64("123456")
//        println(aes.encryptBase64("123456"))
//
//    }
//}