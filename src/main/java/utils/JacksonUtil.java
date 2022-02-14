package utils;

import POJO.BillingAddress;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtil {

    public static BillingAddress deserializeJson(InputStream inputStream, BillingAddress billingAddress) {
        ObjectMapper objectMapper = new ObjectMapper();
        BillingAddress ba = null;
        try{
            ba = objectMapper.readValue(inputStream, billingAddress.getClass());
        }catch(IOException e){
            System.out.println("Error deserializing Billing Address");
            e.printStackTrace();
        }
        return ba;
    }
}