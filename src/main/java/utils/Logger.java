package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    static File logFile;
    static FileWriter myWriter;

    //Logs an error message
    public static void Error(String message){
        String finalMessage = formatMessage(MessageType.ERROR,message);
        writeToFile(finalMessage);
        System.err.println(finalMessage);
    }

    //Logs an informative message (can be used for debugging)
    public static void Info(String message){
        String finalMessage = formatMessage(MessageType.INFO,message);
        writeToFile(finalMessage);
        System.out.println(finalMessage);
    }

    public static void initLogFile(){
        try {
            //Delete the previous log_run file
            logFile = new File("target/results/log_run.txt");
            Files.deleteIfExists(logFile.toPath());

            //Create the file
            if (logFile.createNewFile()) {
                myWriter = new FileWriter("target/results/log_run.txt");
                Info("File created: " + logFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            Logger.Error("An error occurred creating the log file: "+e);
        }
    }

    private static String formatMessage(MessageType type, String message){
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        String formattedDate = df.format(now);
        if(type.equals(MessageType.ERROR))
            return "ERR: "+ formattedDate + ": "+message;
        else //INFO
            return "INFO: "+ formattedDate + ": "+message;
    }

    private static void writeToFile(String message) {
        try {
            myWriter.append(message).append(System.lineSeparator());
        } catch (IOException e) {
            Logger.Error("An error occurred writing to the log: "+e);
        }
    }

    public static void closeWriter(){
        try {
            myWriter.close();
        } catch (IOException e) {
            Logger.Error("An error occurred closing the log file: "+e);
        }
    }
}
