import codecs
import os
import platform

import pymysql
import pymysql.cursors

import db
from util import str_util, time_util


def mysql_type_2_java_type(t, is_kotlin=False):
    if not is_kotlin:
        if t == "int":
            return "Integer"
    else:
        return "Int"

    if t == "bigint":
        return "Long"
    if t == "varchar":
        return "String"
    if t == "text":
        return "String"
    if t == "datetime":
        return "LocalDateTime"
    if t == 'tinyint':
        return "Boolean"
    if t == 'double':
        return "Double"
    if t == 'decimal':
        return "BigDecimal"
    if t == 'timestamp':
        return "LocalDateTime"
    # json的实现方案可能各自选择不一样，还是使用先Object
    # if t == 'json':
    #     return "JSONObject"

    return "Object"


class Generate:

    def __init__(self, config, table_names, output, base_package, auth='', rest=True, request_body=True,
                 doc_type='v2', jdk_version="8", null_validate=False, kotlin=False, standard_http=True):
        """

        :param config:          数据配置对象
        :param table_names:     需要生成的表的名称,如果配置空执行所有表
        :param output:          输出代码根目录
        :param base_package:    基础包名称
        :param auth:            作者名称
        :param rest:            是否使用rest风格
        :param request_body:    入参是否使用RequestBody
        :param doc_type:        文档类型，[v2,v3] 使用v3 配置套jdk17+
        :param jdk_version:     jdk版本，默认8，[8,17]
        :param null_validate:   是否进行空的校验，如果选择是，会增加对应的注解
        :param kotlin:          是否使用kotlin风格
        :param standard_http:   是否遵循标准的http规范设计控制器
        """
        self.config = config
        self.table_names = table_names
        self.output = output
        self.base_package = base_package
        self.rest = rest
        self.request_body = request_body
        self.auth = auth
        self.jdk_version = jdk_version
        if self.auth == '':
            self.auth = platform.node()
        con = self.create_con()
        self.db_name = con.db.decode('utf-8')
        con.close()
        self.doc_type = doc_type
        self.null_validate = null_validate
        self.kotlin = kotlin
        #  提取的表信息数据

    def create_con(self):
        # 创建连接
        con = pymysql.connect(**self.config)
        return con

    def run(self):
        tables = []
        if not self.table_names:
            tbs = self.find_all_table_info()
            for t in tbs:
                tables.append(t['TABLE_NAME'])
        else:
            tables = self.table_names
        for table_name in tables:
            table_info = self.find_table_info(table_name)
            columns = self.find_column_info(table_name)
            # print(json.dumps(columns))
            table_comment = table_info[0]['TABLE_COMMENT']
            class_name = str_util.underline_2_words(table_name)
            self.create_model(columns, class_name, table_comment)
            self.create_mapper(class_name, table_comment)
            self.create_mapper_xml(class_name, table_comment, data=columns)
            self.create_service(class_name, table_comment)
            self.create_service_impl(class_name, table_comment)
            self.create_rest_controller(class_name, table_comment)

    def find_all_table_info(self):
        sql = f"SELECT *  FROM information_schema.TABLES WHERE table_schema = '{self.db_name}'"
        return db.find_v2(self.create_con(), sql)

    def find_table_info(self, table_name):
        sql = f"SELECT *  FROM information_schema.TABLES WHERE table_schema = '{self.db_name}'" \
              f" and TABLE_NAME = '{table_name}'"
        return db.find_v2(self.create_con(), sql)

    def find_column_info(self, table_name):
        sql = f"SELECT *  FROM information_schema.COLUMNS WHERE table_schema = '{self.db_name}'" \
              f" and TABLE_NAME = '{table_name}'"
        return db.find_v2(self.create_con(), sql)

    def create_rest_controller(self, class_name, table_comment):
        if not self.kotlin:
            if self.doc_type == 'v2':
                f = open(f'template/java/restcontroller_v2.java', 'r', encoding='utf-8')
            else:
                f = open(f'template/java/restcontroller_v3.java', 'r', encoding='utf-8')
            template = f.read()
            f.close()
            template = self.replace_g1(template, class_name, table_comment)
            if self.request_body:
                template = template.replace("{{RequestBody}}", "@RequestBody ")
            else:
                template = template.replace("{{RequestBody}}", "")

            template = template.replace("{{low_class_name}}", str_util.first_low(class_name))
            if self.jdk_version == '17':
                template = template.replace("import javax.annotation.Resource;", "import jakarta.annotation.Resource;")
            if not self.rest:
                template = template.replace("@RestController", "@Controller")
                template = template.replace("import org.springframework.web.bind.annotation.RestController;",
                                            "import org.springframework.web.bind.annotation.Controller;")

            file_name = f"{class_name}Controller.java"
            self.write(template, file_name, 'controller')
        else:
            if self.doc_type == 'v2':
                f = open(f'template/kotlin/restcontroller_v2.kt', 'r', encoding='utf-8')
            else:
                f = open(f'template/kotlin/restcontroller_v3.kt', 'r', encoding='utf-8')

            template = f.read()
            f.close()
            template = self.replace_g1(template, class_name, table_comment)
            if self.request_body:
                template = template.replace("{{RequestBody}}", "@RequestBody ")
            else:
                template = template.replace("{{RequestBody}}", "")

            template = template.replace("{{low_class_name}}", str_util.first_low(class_name))
            if self.jdk_version == '17':
                template = template.replace("import javax.annotation.Resource", "import jakarta.annotation.Resource")
            if not self.rest:
                template = template.replace("@RestController", "@Controller")
                template = template.replace("import org.springframework.web.bind.annotation.RestController",
                                            "import org.springframework.web.bind.annotation.Controller")

            file_name = f"{class_name}Controller.kt"
            self.write(template, file_name, 'controller')

    def create_common_file(self, class_name, table_comment, suffix, sub_path):
        # cap_module = suffix.capitalize()
        cap_module = suffix[0:1].upper() + suffix[1:]
        low_module = suffix.lower()
        file_name = f"{class_name}{cap_module}.java"
        read_name = f'template/java/{low_module}.java'
        if self.kotlin:
            file_name = f"{class_name}{cap_module}.kt"
            read_name = f'template/kotlin/{low_module}.kt'
        f = open(read_name, 'r', encoding='utf-8')
        template = f.read()
        f.close()
        template = self.replace_g1(template, class_name, table_comment)
        self.write(template, file_name, sub_path)

    def create_service_impl(self, class_name, table_comment):
        self.create_common_file(class_name, table_comment, 'ServiceImpl', 'service/impl')

    def create_service(self, class_name, table_comment):
        self.create_common_file(class_name, table_comment, 'service', 'service')

    def create_mapper(self, class_name, table_comment):
        self.create_common_file(class_name, table_comment, 'mapper', 'mapper')

    def create_mapper_xml(self, class_name, table_comment, data):
        results = []
        index = 0
        data_len = len(data)
        for d in data:
            col_name = d['COLUMN_NAME']
            java_property = str_util.sql_name_2_property(col_name)
            if index == 0:
                result = f'<result property="{java_property}" column="{col_name}"/>\n'
            else:
                if data_len == index + 1:
                    result = f'\t\t<result property="{java_property}" column="{col_name}"/>'
                else:
                    result = f'\t\t<result property="{java_property}" column="{col_name}"/>\n'
            results.append(result)
            index += 1

        file_name = f"{class_name}Mapper.xml"
        read_name = f'template/java/mapper.xml'
        f = open(read_name, 'r', encoding='utf-8')
        template = f.read()
        f.close()
        template = self.replace_g1(template, class_name, table_comment)
        template = template.replace("{{results}}", "".join(results))
        output_path = self.output
        if not os.path.exists(output_path):
            os.makedirs(output_path)
        source_code_path = f"{output_path}/src/main/resource/mapper"
        if not os.path.exists(source_code_path):
            os.makedirs(source_code_path)
        out_file = codecs.open(f'{source_code_path}/{file_name}', 'w', 'utf-8')
        out_file.writelines(template)

    def create_model(self, data, class_name, table_comment):
        field_lines = []
        index = 0
        for d in data:
            col_name = d['COLUMN_NAME']
            java_property = str_util.sql_name_2_property(col_name)
            mysql_type = d['DATA_TYPE']
            java_type = mysql_type_2_java_type(mysql_type,self.kotlin)
            comment = d['COLUMN_COMMENT']
            if index == 0:
                pre_t = ""
                if d['COLUMN_KEY'] == 'PRI':
                    if d['EXTRA'] == 'auto_increment':
                        field_lines.append(f'{pre_t}@TableId(value = "id", type = IdType.AUTO)\n')
                        pre_t = "\t"
                    else:
                        field_lines.append(f'{pre_t}@TableId(value = "id", type = IdType.INPUT)\n')
                        pre_t = "\t"

                if self.null_validate:
                    if d['IS_NULLABLE'] == 'NO':
                        # 非空要求
                        field_lines.append(f'{pre_t}@NotNull(message = "{comment}不能为空")\n')
                        pre_t = "\t"
                        if java_type == 'String':
                            field_lines.append(f'{pre_t}@NotEmpty(message = "{comment}不能为空")\n')
                            pre_t = "\t"

                if self.doc_type == 'v2':
                    annotation = f'{pre_t}@ApiModelProperty(value = "{comment}")\n'
                    pre_t = "\t"

                else:
                    annotation = f'{pre_t}@Schema(description = "{comment}")\n'
                    pre_t = "\t"

            else:
                if d['COLUMN_KEY'] == 'PRI':
                    if d['EXTRA'] == 'auto_increment':
                        field_lines.append(f'\t@TableId(value = "id", type = IdType.AUTO)\n')
                    else:
                        field_lines.append(f'\t@TableId(value = "id", type = IdType.INPUT)\n')

                if self.null_validate:
                    if d['IS_NULLABLE'] == 'NO':
                        # 非空要求
                        field_lines.append(f'\t@NotNull(message = "{comment}不能为空")\n')
                        if java_type == 'String':
                            field_lines.append(f'\t@NotEmpty(message = "{comment}不能为空")\n')
                if self.doc_type == 'v2':
                    annotation = f'\t@ApiModelProperty(value = "{comment}")\n'
                else:
                    annotation = f'\t@Schema(description = "{comment}")\n'
            if self.kotlin:
                line = f"\tvar {java_property}:{java_type}?=null\n\n"
            else:
                line = f"\tprivate {java_type} {java_property};\n\n"

            # result = f'<result column="{col_name}" property="{java_property}"/>\n'

            field_lines.append(annotation)
            field_lines.append(line)
            index += 1
        if self.kotlin:
            file_name = f"{class_name}.kt"
            if self.doc_type == 'v2':
                f = open(f'./template/kotlin/model_v2.kt')
            else:
                f = open(f'template/kotlin/model_v3.kt')
        else:
            file_name = f"{class_name}.java"

            if self.doc_type == 'v2':
                f = open(f'./template/java/model_v2.java')
            else:
                f = open(f'template/java/model_v3.java')

        lines = f.read()
        f.close()
        fields = "".join(field_lines)
        template = lines.replace("{{fields}}", fields)
        handle_import = [
            ["private LocalDate", "import java.time.LocalDate"],
            ["private LocalDateTime", "import java.time.LocalDateTime"],
            ["private BigDecimal", "import java.math.BigDecimal"]
        ]
        if self.kotlin:
            handle_import = [
                [":LocalDate", "import java.time.LocalDate"],
                [":LocalDateTime", "import java.time.LocalDateTime"],
                [":BigDecimal", "import java.math.BigDecimal"]
            ]
        if self.jdk_version == '8':
            handle_import.append(["@NotNull", "import javax.validation.constraints.NotNull"])
            handle_import.append(["@NotEmpty", "import javax.validation.constraints.NotEmpty"])
        if self.jdk_version == '17':
            handle_import.append(["@NotNull", "import jakarta.validation.constraints.NotNull"])
            handle_import.append(["@NotEmpty", "import jakarta.validation.constraints.NotEmpty"])

        for h in handle_import:
            if not self.kotlin:
                h[1] = f"{h[1]};"
        imports = []
        for h in handle_import:
            if h[0] in template:
                imports.append(h[1])

        template = template.replace("{{import_package}}", "\n".join(imports))
        template = self.replace_g1(template, class_name, table_comment)
        self.write(template, file_name, 'model')

    def write(self, template, file_name, suffix):
        output_path = self.output
        if not os.path.exists(output_path):
            os.makedirs(output_path)
        code_relative_path = "/".join(self.base_package.split("."))
        if self.kotlin:
            source_code_path = f"{output_path}/src/main/kotlin/{code_relative_path}/{suffix}"
        else:
            source_code_path = f"{output_path}/src/main/java/{code_relative_path}/{suffix}"
        if not os.path.exists(source_code_path):
            os.makedirs(source_code_path)
        out_file = codecs.open(f'{source_code_path}/{file_name}', 'w', 'utf-8')
        out_file.writelines(template)

    def replace_g1(self, template, class_name, table_comment):
        template = template.replace("{{class_name}}", class_name)
        template = template.replace("{{package_name}}", self.base_package)
        template = template.replace("{{table_comment}}", table_comment)
        template = template.replace("{{author}}", self.auth)
        template = template.replace("{{today}}", time_util.today())
        return template
