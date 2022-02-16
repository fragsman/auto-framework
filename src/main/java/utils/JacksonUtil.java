package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtil{

    public static <T> T deserializeJson(String filename, Class<T> T) {
        InputStream inputStream = JacksonUtil.class.getClassLoader().getResourceAsStream(filename);
        if(inputStream==null){
            System.out.println("input is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        T ba = null;
        try{
            ba = (T) objectMapper.readValue(inputStream, T);
        }catch(IOException e){
            System.out.println("Error deserializing the data object");
            e.printStackTrace();
        }
        return ba;
    }
}