package top.daozhang.account.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.dubbo.config.annotation.DubboService
import top.daozhang.account.entity.ResourceExtra
import top.daozhang.account.mapper.ResourceExtraMapper

/**
 * <p>
 *  资源额外补充信息服务实现类
 * </p>
 *
 * @author 邱道长
 * @since 2023-02-27
 */
@DubboService
open class ResourceExtraServiceImpl : ServiceImpl<ResourceExtraMapper, ResourceExtra>(), ResourceExtraService {
    override fun findWithRid(rid: Long): List<ResourceExtra> {

//        return lambdaQuery().eq(ResourceExtra::rid,rid).list()
//        val q = LambdaQueryWrapper<ResourceExtra>()
//        q.eq(ResourceExtra::rid, rid)
//        return baseMapper.selectList(q)
        return baseMapper.findByRid(rid)
    }


}
