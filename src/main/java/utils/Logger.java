package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void Log(String message){
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        String formattedDate = df.format(now);

        //TODO: Write to a file

        System.out.println("LOG: "+ formattedDate + ": "+message);
    }

}
