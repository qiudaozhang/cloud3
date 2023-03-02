package top.daozhang.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.daozhang.account.entity.Auth;
import top.daozhang.account.entity.ResourceExtra;

import java.util.List;

public interface ResourceExtraMapper extends BaseMapper<ResourceExtra> {


    List<ResourceExtra> findByRid(@Param("rid") Long rid);


    List<ResourceExtra> findByPid(@Param("pid") Long pid);

    int deleteByPid(@Param("pid") Long pid);

    int removeInPidRid(List<Long> idList);
}
