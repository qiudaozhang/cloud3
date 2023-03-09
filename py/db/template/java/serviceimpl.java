package {{package_name}}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import {{package_name}}.model.{{class_name}};
import {{package_name}}.mapper.{{class_name}}Mapper;
import org.springframework.stereotype.Service;
import {{package_name}}.service.{{class_name}}Service;

/**
* @author {{author}}
* @since {{today}}
* {{table_comment}}服务接口实现类
*/
@Service
public class {{class_name}}ServiceImpl extends ServiceImpl<{{class_name}}Mapper, {{class_name}}> implements {{class_name}}Service {

}