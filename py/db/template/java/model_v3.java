package {{package_name}}.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
{{import_package}}

@Data
@Schema(description="{{table_comment}}")
public class {{class_name}} implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    {{fields}}

}