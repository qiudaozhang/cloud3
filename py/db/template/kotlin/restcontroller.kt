package {{package_name}}.controller;


import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource
import {{package_name}}.model.{{class_name}}
import {{package_name}}.service.{{class_name}}Service
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.annotation.Resource

/**
* @author {{author}}
* @since {{today}}
* {{table_comment}}控制器
*/
@RestController
@Api(tags = "{{class_name}}控制器")
@RequestMapping("api/{{class_name}}")
public class {{class_name}}Controller {

    @Resource
    lateinit var {{low_class_name}}Service: {{class_name}}Service

    @PostMapping
    @ApiOperation(value = "新增")
    fun post({{RequestBody}}{{class_name}} param){
        {{low_class_name}}Service.save(param);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    fun put({{RequestBody}}{{class_name}} param){
        {{low_class_name}}Service.updateById(param);
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "删除")
    fun delete(@PathVariable("id") Serializable id ){
        {{low_class_name}}Service.removeById(id);
    }


    @GetMapping("{id}")
    @ApiOperation(value = "查询")
    fun get(@PathVariable("id") Serializable id ):{{class_name}}{
        return {{low_class_name}}Service.getById(id);
    }

}