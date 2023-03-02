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
        return baseMapper.findByRid(rid)
    }

    override fun findByPid(pid: Long): List<ResourceExtra> {
        return  baseMapper.findByPid(pid)
    }

    override fun removeInPidRid(idList: List<Long>): Boolean {
        baseMapper.removeInPidRid(idList)
        return true
    }


}
