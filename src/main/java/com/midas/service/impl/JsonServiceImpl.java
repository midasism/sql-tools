package com.midas.service.impl;

import com.midas.constants.MySQLParameterTypeEnum;
import com.midas.domain.MySQLParameter;
import com.midas.domain.MySQLResult;
import com.midas.service.JsonService;
import com.midas.utils.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class JsonServiceImpl implements JsonService {

    private static Pattern numberPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    //    private static Pattern stringPattern = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z\\d]+$");
    private static Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private static Pattern dateTimePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    private static final String KEY_SEPARATOR = "&";

    @Override
    public MySQLResult generateSQLByJSON(String jsonStr) {
        List<MySQLParameter> mySQLParameters = buildMySQLParameter(jsonStr);

        return generateSQL(mySQLParameters);
    }

    private List<MySQLParameter> buildMySQLParameter(String jsonStr) {
        Map<String, Object> paramMap = JsonUtils.parseJSON(jsonStr);
        if (CollectionUtils.isEmpty(paramMap)) {
            return new ArrayList<>();
        }

        List<MySQLParameter> mySQLParameters = new ArrayList<>(paramMap.size());
        paramMap.forEach((key, value) -> {
            key = key.replace("\"", "");
            String formattedValue = String.valueOf(value).replace("\"", "");

            String[] splitRes = key.split(KEY_SEPARATOR);
            MySQLParameter parameter = new MySQLParameter();
            parameter.setName(splitRes[0]);
            parameter.setLength(splitRes.length > 1 ? Integer.valueOf(splitRes[1]) : 0);
            parameter.setType(parseMySQLParameterType(formattedValue));
            mySQLParameters.add(parameter);
        });
        return mySQLParameters;
    }

    /**
     * 根据字段值反推字段类型
     */
    private String parseMySQLParameterType(String value) {
        Matcher numberMatcher = numberPattern.matcher(value);
        if (numberMatcher.matches()) {
            return MySQLParameterTypeEnum.INT.getValue();
        }
        Matcher dateMatcher = datePattern.matcher(value);
        if (dateMatcher.matches()) {
            return MySQLParameterTypeEnum.DATE.getValue();
        }
        Matcher dateTimeMatcher = dateTimePattern.matcher(value);
        if (dateTimeMatcher.matches()) {
            return MySQLParameterTypeEnum.DATETIME.getValue();
        }
        return MySQLParameterTypeEnum.VARCHAR.getValue();
    }

    private MySQLResult generateSQL(List<MySQLParameter> mySQLParameters) {
        if (CollectionUtils.isEmpty(mySQLParameters)) {
            return null;
        }
        MySQLResult result = new MySQLResult();
        result.setCreateTableSQL(generateCreateTableSQL(mySQLParameters));
        // TODO 新增字段语句
        return result;
    }

    /**
     * 生成MySQL建表语句
     */
    private String generateCreateTableSQL(List<MySQLParameter> mySQLParameters) {
        StringBuilder sb = new StringBuilder("""
                CREATE TABLE IF NOT EXISTS `tablename` (
                """);

        String fieldStatements = mySQLParameters.stream().map(this::formatFieldToTableBuildingStatement).collect(Collectors.joining(",\n"));
        sb.append(fieldStatements);

        sb.append(""" 
                ,
                PRIMARY KEY (`name`)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
                    """);
        return sb.toString();
    }

    private String formatFieldToTableBuildingStatement(MySQLParameter mySQLParameter) {
        // TODO 根据字段属性生成SQL
        // "`name` VARCHAR(64) NOT NULL"
        return "";
    }
}
