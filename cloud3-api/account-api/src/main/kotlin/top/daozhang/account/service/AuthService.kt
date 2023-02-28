package top.daozhang.account.service;

import top.daozhang.account.entity.Auth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 邱道长
 * @since 2023-02-27
 */
interface AuthService : IService<Auth> {


    fun hasAccount(username:String?,phone:String?,email:String?):Boolean
    fun findAccount(username:String?,phone:String?,email:String?):Auth?

}
