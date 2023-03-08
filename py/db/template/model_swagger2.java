package {{package_name}}.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @author {{author}}
* @since {{today}}
*/
@Data
@ApiModel(value="{{table_comment}}")
public class {{class_name}} implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    {{fields}}

}