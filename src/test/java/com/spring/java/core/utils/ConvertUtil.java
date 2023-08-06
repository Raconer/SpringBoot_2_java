package com.spring.java.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;

public class ConvertUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getJsonString(Object data) {
        String jsonBody = null;
        try{
           jsonBody = objectMapper.writeValueAsString(data);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }

        return jsonBody;
    }

    public static HashMap<String, Object> getResult(String resultStr) {
        try{
            var data = objectMapper.readValue(resultStr, HashMap.class);
            return (HashMap<String, Object>) data.get("result");
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

       return null;
    }

    public static <T> T getResult(String resultStr, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(resultStr, clazz);
    }

}
