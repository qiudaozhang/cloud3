package {{package_name}}.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.annotation.Resource;
import {{package_name}}.model.{{class_name}};
import {{package_name}}.service.{{class_name}}Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.Serializable;

/**
* @author {{author}}
* @since {{today}}
* {{table_comment}}控制器
*/
@RestController
@Api(tags = "{{table_name}}控制器")
@RequestMapping("api/{{class_name}}")
public class {{class_name}}Controller {


    @Resource
    private {{class_name}}Service {{low_class_name}}Service;


    @PostMapping
    @ApiOperation(value = "新增")
    public void post({{RequestBody}}{{class_name}} param){
        {{low_class_name}}Service.save(param);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public void put({{RequestBody}}{{class_name}} param){
        {{low_class_name}}Service.updateById(param);
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable("id") Serializable id ){
        {{low_class_name}}Service.removeById(id);
    }


    @GetMapping("{id}")
    @ApiOperation(value = "查询")
    public {{class_name}} get(@PathVariable("id") Serializable id ){
        return {{low_class_name}}Service.getById(id);
    }

}