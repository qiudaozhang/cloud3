package top.daozhang.account.service

import com.baomidou.mybatisplus.extension.service.IService
import top.daozhang.account.entity.ResourceExtra

interface ResourceExtraService:IService<ResourceExtra> {

    fun findWithRid(rid:Long):List<ResourceExtra>




}