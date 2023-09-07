package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtil{

	/*
	 * The resources to be deserialized by this function must be located under src/main/resources
	 * If that is the case we only need to pass the name to this function, in case they are within
	 * another folder you need to specifi it. e.g. 'newfolder/theResource.json'
	 */
    public static <T> T deserializeJson(String filename, Class<T> T) {
        InputStream inputStream = JacksonUtil.class.getClassLoader().getResourceAsStream(filename);
        if(inputStream==null){
            Logger.Error("deserializeJson: input is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        T ba = null;
        try{
            ba = (T) objectMapper.readValue(inputStream, T);
        }catch(IOException e){
            Logger.Error("deserializeJson: Error deserializing the data object: "+filename);
            e.printStackTrace();
        }
        return ba;
    }
}