package top.daozhang.account.service

import com.baomidou.mybatisplus.extension.service.IService
import top.daozhang.account.entity.Resource

interface ResourceService:IService<Resource> {

//    fun removeChild(parent:Long, child:Long):Boolean


    /**
     * 移除资源
     */
    fun deleteOne(id:Long):Boolean
}