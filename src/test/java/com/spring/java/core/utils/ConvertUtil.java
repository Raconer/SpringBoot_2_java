package com.spring.java.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class ConvertUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getJsonString(Object data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }

    public static HashMap<String, Object> getResult(String resultStr) throws JsonProcessingException {
        var data = objectMapper.readValue(resultStr, HashMap.class);
        return (HashMap<String, Object>) data.get("result");
    }

    public static <T> T getResult(String resultStr, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(resultStr, clazz);
    }

}
