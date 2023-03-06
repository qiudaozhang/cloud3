package top.daozhang.system.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.dubbo.config.annotation.DubboService
import system.entity.Dict
import system.service.DictService
import top.daozhang.system.mapper.DictMapper

@DubboService
open class DictServiceImpl:ServiceImpl<DictMapper,Dict>(),DictService {
    override fun txSave() {
        println("dict save")
        throw RuntimeException("异常")
    }
}