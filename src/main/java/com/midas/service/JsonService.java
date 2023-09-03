package com.midas.service;

import com.midas.domain.MySQLResult;

public interface JsonService {

    /**
     * 根据JSON生成SQL语句，包括建表、字段插入
     * @param jsonStr
     */
    MySQLResult generateSQLByJSON(String jsonStr);
}
