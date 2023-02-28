package top.daozhang.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.daozhang.account.entity.Auth;

public interface AuthMapper extends BaseMapper<Auth> {


    Long hasAccount(@Param("username") String username, @Param("phone") String phone, @Param("email") String email);

    Auth findOneAccount(@Param("username") String username, @Param("phone") String phone, @Param("email") String email);
}
