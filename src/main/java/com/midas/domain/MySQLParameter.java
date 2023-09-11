package com.midas.domain;

import com.midas.constants.MySQLParameterTypeEnum;
import lombok.Data;

/**
 * 数据库字段
 */
@Data
public class MySQLParameter {

    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段类型
     * @see MySQLParameterTypeEnum
     */
    private String type;

    /**
     * 字段长度
     */
    private Integer length;

    // TODO 是否非空
}
