package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    //Logs an error message
    public static void Error(String message){
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        String formattedDate = df.format(now);

        //TODO: Write to a file

        System.err.println("ERR: "+ formattedDate + ": "+message);
    }

    //Logs an informative message (can be used for debugging)
    public static void Info(String message){
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        String formattedDate = df.format(now);

        //TODO: Write to a file

        System.out.println("LOG: "+ formattedDate + ": "+message);
    }

}
