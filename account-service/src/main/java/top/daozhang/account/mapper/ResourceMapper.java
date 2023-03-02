package top.daozhang.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.daozhang.account.entity.Resource;
import top.daozhang.account.vo.ResourceVo;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {

    List<ResourceVo> findSonByPid(Long id );


    int removeByIds(List<Long> list);

}
