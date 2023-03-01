package top.daozhang.account.service

import com.baomidou.mybatisplus.extension.service.IService
import top.daozhang.account.entity.Resource
import top.daozhang.account.vo.ResourceVo

interface ResourceService:IService<Resource> {



    fun createOne(resource: Resource):Boolean

    /**
     * 移除资源
     */
    fun deleteOne(id:Long):Boolean

    /**
     * 查询一个资源（包含所有的子资源) 需要全部挂载完毕
     */
    fun findOne(id:Long):ResourceVo?

}