import pymysql
import pymysql.cursors

from db.Generate import Generate

config = {
    'host': '127.0.0.1',
    'port': 3354,  # MySQL默认端口
    'user': 'root',  # mysql默认用户名
    'password': 'root',
    'db': 'cloud3',  # 数据库
    'charset': 'utf8mb4',
    'cursorclass': pymysql.cursors.DictCursor,
}
g = Generate(config, [], 'E://generate_code', 'top.daozhang', '邱道长', jdk_version='8', request_body=False, rest=True,
             null_validate=True, doc_type='v3', kotlin=True)
g.run()
