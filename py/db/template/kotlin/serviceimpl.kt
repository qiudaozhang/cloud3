package {{package_name}}.service.impl

import com.baomidou.mybatisplus.extension.service.IService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import {{package_name}}.model.{{class_name}}
import {{package_name}}.mapper.{{class_name}}Mapper

/**
* @author {{author}}
* @since {{today}}
* {{table_comment}}服务接口实现类
*/
@Service
class {{class_name}}ServiceImpl:ServiceImpl<{{class_name}}Mapper, {{class_name}}>(),{{class_name}}Service {

}