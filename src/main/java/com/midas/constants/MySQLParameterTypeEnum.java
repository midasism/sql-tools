package com.midas.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * MySQL字段类型
 */
public enum MySQLParameterTypeEnum {

    INT("int"),
    VARCHAR("varchar"),
    DATE("date"),
    DATETIME("datetime"),
    ;

    private final String value;

    MySQLParameterTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MySQLParameterTypeEnum getByValue(String value) {
        return valueMap.get(value);
    }

    private static final Map<String, MySQLParameterTypeEnum> valueMap = new HashMap<>();
    static {
        valueMap.put(INT.value, INT);
        valueMap.put(VARCHAR.value, VARCHAR);
        valueMap.put(DATE.value, DATE);
        valueMap.put(DATETIME.value, DATETIME);
    }
}
