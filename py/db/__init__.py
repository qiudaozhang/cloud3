# -*- coding: utf-8 -*-

# 导入必要模块

from config import DB_PORT, DB_USERNAME, DB_PASSWORD, DB_HOST, DB_NAME

host = DB_HOST
import pymysql.cursors
import pymysql

# 连接配置信息
config = {
    'host': DB_HOST,
    'port': DB_PORT,  # MySQL默认端口
    'user': DB_USERNAME,  # mysql默认用户名
    'password': DB_PASSWORD,
    'db': DB_NAME,  # 数据库
    'charset': 'utf8mb4',
    'cursorclass': pymysql.cursors.DictCursor,
}

# 创建连接
con = pymysql.connect(**config)


# 执行sql语句
def find(sql):
    try:
        with con.cursor() as cursor:
            cursor.execute(sql)
            result = cursor.fetchall()
            return result
    finally:
        con.close()
