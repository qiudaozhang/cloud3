package {{package_name}}.model

import java.io.Serial
import java.io.Serializable
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotEmpty

@ApiModel(title="{{table_comment}}")
class {{class_name}}:Serializable {

    {{fields}}

    companion object {
        @Serial
        private const val serialVersionUID: Long = -1L
    }

}