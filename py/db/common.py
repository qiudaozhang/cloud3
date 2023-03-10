import db
# def find_table_info(table_name):
from config import DB_NAME
import codecs
import os
from util import str_util


def show_table():
    sql = "show tables"
    data = db.find(sql)
    return data


def find_table_info(table_name):
    # sql = f"SELECT *  FROM information_schema.tables WHERE table_schema = '{DB_NAME}'"
    sql = f"SELECT *  FROM information_schema.COLUMNS WHERE table_schema = '{DB_NAME}' and TABLE_NAME = '{table_name}'"
    return db.find(sql)


# data = show_table()
# cur.execute("SELECT table_name FROM information_schema.tables WHERE table_schema = 'my_database'")
# for d in data:
#     print(d['Tables_in_cloud3'])


def mysql_type_2_java_type(t):
    if t == "int":
        return "Integer"
    if t == "bigint":
        return "Long"
    if t == "varchar":
        return "String"
    if t == "datetime":
        return "LocalDateTime"
    return "Object"


def create_model(output_path, data, package_name, class_name):
    field_lines = []
    index = 0
    for d in data:
        # print(d)
        col_name = d['COLUMN_NAME']
        mysql_type = d['DATA_TYPE']
        java_type = mysql_type_2_java_type(mysql_type)
        comment = d['COLUMN_COMMENT']
        if index == 0:
            annotation = f'@Schema(description = "{comment}");\n'
        else:
            annotation = f'\t@Schema(description = "{comment}");\n'

        line = f"\tprivate {java_type} {col_name};\n\n"
        # print(annotation)
        # print(line)
        field_lines.append(annotation)
        field_lines.append(line)
        index += 1
    f = open(f'./template/model.java')
    lines = f.read()
    f.close()
    fields = "".join(field_lines)

    template = lines.replace("{{fields}}", fields)
    template = template.replace("{{class_name}}", class_name)
    template = template.replace("{{package_name}}", package_name)
    file_name = f"{class_name}.java"

    if not os.path.exists(output_path):
        os.makedirs(output_path)

    code_relative_path = "/".join(package_name.split("."))
    source_code_path = f"{output_path}/src/main/java/{code_relative_path}/model"
    if not os.path.exists(source_code_path):
        os.makedirs(source_code_path)
    out_file = codecs.open(f'{source_code_path}/{file_name}', 'w', 'utf-8')
    out_file.writelines(template)


def create_common_file(package_name, class_name, output_path, suffix):
    end_package_name = suffix
    if suffix.endswith("Impl"):
        end_package_name = end_package_name.replace("Impl", "").lower() + "/impl"

    if suffix.startswith("rest"):
        end_package_name = end_package_name.replace("Rest", "").lower()

    f = open(f'./template/{suffix.lower()}.java','r',encoding='utf-8')
    pure_file_name = f"{class_name}{suffix}"
    template = f.read()
    f.close()
    template = template.replace("{{class_name}}", class_name)
    template = template.replace("{{package_name}}", f"{package_name}")
    if suffix.startswith("Rest"):
        template = template.replace("{{low_class_name}}", str_util.first_low(class_name))

    file_name = f"{pure_file_name}.java"
    if not os.path.exists(output_path):
        os.makedirs(output_path)
    code_relative_path = "/".join(package_name.split("."))
    source_code_path = f"{output_path}/src/main/java/{code_relative_path}/{end_package_name}"
    if not os.path.exists(source_code_path):
        os.makedirs(source_code_path)
    out_file = codecs.open(f'{source_code_path}/{file_name}', 'w', 'utf-8')
    out_file.writelines(template)


def generate_model(package_name, table_name, output_path='e://generate_code'):
    data = find_table_info(table_name)
    class_name = table_name.capitalize()
    create_model(output_path, data, package_name, class_name)
    create_common_file(package_name, class_name, output_path, 'Mapper')
    create_common_file(package_name, class_name, output_path, 'Service')
    create_common_file(package_name, class_name, output_path, 'ServiceImpl')
    create_common_file(package_name, class_name, output_path, 'RestController')


generate_model("top.daozhang", "auth")
