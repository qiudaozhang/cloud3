package top.daozhang.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.dubbo.config.annotation.DubboService
import top.daozhang.account.entity.Auth
import top.daozhang.account.mapper.AuthMapper

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 邱道长
 * @since 2023-02-27
 */
@DubboService
open class AuthServiceImpl : ServiceImpl<AuthMapper, Auth>(), AuthService {
    override fun hasAccount(username: String?, phone: String?, email: String?): Boolean {
        return baseMapper.hasAccount(username,phone,email)>0
    }

    override fun findAccount(username: String?, phone: String?, email: String?): Auth? {
        return baseMapper.findOneAccount(username,phone,email)
    }

}
