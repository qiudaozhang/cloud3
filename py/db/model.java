package {{package_name}};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;


@Data
public class {{class_name}} implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    {{fields}}

}