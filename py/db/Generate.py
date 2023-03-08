import codecs
import os
import platform

import pymysql
import pymysql.cursors
import json
import db
from util import str_util, time_util


def mysql_type_2_java_type(t):
    if t == "int":
        return "Integer"
    if t == "bigint":
        return "Long"
    if t == "varchar":
        return "String"
    if t == "datetime":
        return "LocalDateTime"
    if t == 'tinyint':
        return "Boolean"
    if t == 'double':
        return "Double"
    if t == 'decimal':
        return "BigDecimal"
    # json的实现方案可能各自选择不一样，还是使用先Object
    # if t == 'json':
    #     return "JSONObject"

    return "Object"


class Generate:

    def __init__(self, config, table_names, output, base_package, auth='', rest=True, request_body=True,
                 doc_type='swagger2', jdk_version="8",null_validate=False):
        """

        :param config:          数据配置对象
        :param table_names:     需要生成的表的名称,如果配置空执行所有表
        :param output:          输出代码根目录
        :param base_package:    基础包名称
        :param auth:            作者名称
        :param rest:            是否使用rest风格
        :param request_body:    入参是否使用RequestBody
        :param doc_type:        文档类型，[swagger2,openapiV3]
        :param jdk_version:     jdk版本，默认8，[8,17]
        :param null_validate:   是否进行空的校验，如果选择是，会增加对应的注解
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
        for table_name in tables:
            table_info = self.find_table_info(table_name)
            columns = self.find_column_info(table_name)
            # print(columns)
            print(json.dumps(columns))
            # print(table_info)
            table_comment = table_info[0]['TABLE_COMMENT']
            class_name = str_util.underline_2_words(table_name)
            self.create_model(columns, class_name, table_comment)
            self.create_mapper(class_name, table_comment)
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
        f = open(f'./template/restcontroller.java', 'r', encoding='utf-8')
        template = f.read()
        f.close()
        template = self.replace_g1(template, class_name, table_comment)
        if self.request_body:
            template = template.replace("{{RequestBody}}", "@RequestBody ")
        else:
            template = template.replace("{{RequestBody}}", "")

        template = template.replace("{{low_class_name}}", str_util.first_low(class_name))
        if self.jdk_version == '17':
            template = template.replace("import javax.annotation.Resource;", "import jakarta.annotation.Resource")
        if not self.rest:
            template = template.replace("@RestController", "@Controller")
            template = template.replace("import org.springframework.web.bind.annotation.RestController;",
                                        "import org.springframework.web.bind.annotation.Controller;")

        file_name = f"{class_name}Controller.java"
        self.write(template, file_name, 'controller')

    def create_service_impl(self, class_name, table_comment):
        f = open(f'./template/serviceimpl.java', 'r', encoding='utf-8')
        template = f.read()
        f.close()
        template = self.replace_g1(template, class_name, table_comment)
        file_name = f"{class_name}ServiceImpl.java"
        self.write(template, file_name, 'service/impl')

    def create_service(self, class_name, table_comment):
        f = open(f'./template/service.java', 'r', encoding='utf-8')
        template = f.read()
        f.close()
        template = self.replace_g1(template, class_name, table_comment)
        file_name = f"{class_name}Service.java"
        self.write(template, file_name, 'service')

    def create_mapper(self, class_name, table_comment):

        f = open(f'./template/mapper.java', 'r', encoding='utf-8')
        template = f.read()
        f.close()
        template = self.replace_g1(template, class_name, table_comment)
        file_name = f"{class_name}Mapper.java"
        self.write(template, file_name, 'mapper')

    def create_model(self, data, class_name, table_comment):
        field_lines = []
        index = 0
        for d in data:
            # print(d)
            col_name = d['COLUMN_NAME']
            mysql_type = d['DATA_TYPE']
            java_type = mysql_type_2_java_type(mysql_type)
            comment = d['COLUMN_COMMENT']
            if d['COLUMN_KEY'] == 'PRI':
                if d['EXTRA'] == 'auto_increment':
                    field_lines.append(f'@TableId(value = "id", type = IdType.AUTO)\n')
                else:
                    field_lines.append(f'@TableId(value = "id", type = IdType.Input)\n')

            #     @NotNull(message = "{{column_name}}不能为空")
            if self.null_validate:
                if d['IS_NULLABLE'] == 'NO':
                    # 非空要求
                    field_lines.append(f'\t@NotNull(message = "{comment}不能为空")\n')

            if index == 0:
                if self.doc_type == 'swagger2':
                    annotation = f'\t@ApiModelProperty(value = "{comment}");\n'
                else:
                    annotation = f'\t@Schema(description = "{comment}");\n'
            else:
                if self.doc_type == 'swagger2':
                    annotation = f'\t@ApiModelProperty(value = "{comment}");\n'
                else:
                    annotation = f'\t@Schema(description = "{comment}");\n'

            line = f"\tprivate {java_type} {col_name};\n\n"
            field_lines.append(annotation)
            field_lines.append(line)
            index += 1
        if self.doc_type == 'swagger2':
            f = open(f'./template/model_swagger2.java')
        else:
            f = open(f'./template/model.java')
        lines = f.read()
        f.close()
        fields = "".join(field_lines)
        template = lines.replace("{{fields}}", fields)
        template = self.replace_g1(template, class_name, table_comment)
        file_name = f"{class_name}.java"
        self.write(template, file_name, 'model')

    def write(self, template, file_name, suffix):
        output_path = self.output
        if not os.path.exists(output_path):
            os.makedirs(output_path)
        code_relative_path = "/".join(self.base_package.split("."))
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
