package {{package_name}}.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
* @author {{author}}
* @since {{today}}
* {{table_comment}}控制器
*/
@RestController
@Tags(name = "{{table_name}}控制器")
@RequestMapping("api/{{class_name}}")
public class {{class_name}}Controller {


    @Reource
    private {{class_name}}Service {{low_class_name}}Service;


    @PostMapping
    @Operation(summary = "新增")
    public void post({{RequestBody}}{{class_name}} param){
        {{low_class_name}}Service.save(param);
    }

    @PutMapping
    @Operation(summary = "修改")
    public void put({{RequestBody}}{{class_name}} param){
        {{low_class_name}}Service.updateById(param);
    }


    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    public void delete(@PathVariable("id") Serializable id ){
        {{low_class_name}}Service.removeById(id);
    }


    @GetMapping("{id}")
    @Operation(summary = "查询")
    public {{class_name}} get(@PathVariable("id") Serializable id ){
        return {{low_class_name}}Service.getById(id);
    }

}