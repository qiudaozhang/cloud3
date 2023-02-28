package top.daozhang.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.dubbo.config.annotation.DubboService
import top.daozhang.account.entity.Auth
import top.daozhang.account.entity.Resource
import top.daozhang.account.mapper.AuthMapper
import top.daozhang.account.mapper.ResourceMapper

/**
 * <p>
 *  资源服务实现类
 * </p>
 *
 * @author 邱道长
 * @since 2023-02-27
 */
@DubboService
open class ResourceServiceImpl : ServiceImpl<ResourceMapper, Resource>(), ResourceService {
    //    override fun removeChild(parent: Long, child: Long): Boolean {
//        lambdaUpdate().eq()
//    }
    override fun deleteOne(id: Long): Boolean {
        TODO("Not yet implemented")
    }


}
