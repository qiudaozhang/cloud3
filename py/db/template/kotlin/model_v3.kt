package {{package_name}}.model

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serial
import java.io.Serializable
{{import_package}}


@Schema(title="{{table_comment}}")
class {{class_name}}:Serializable {

    {{fields}}

    companion object {
        @Serial
        private const val serialVersionUID: Long = -1L
    }

}