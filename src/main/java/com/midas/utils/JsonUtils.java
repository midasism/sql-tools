package com.midas.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @author midasgao
 */
public class JsonUtils {

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, Object> parseJSON(String jsonStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            System.out.println("json转换失败");
        }
        return map;
    }

}
