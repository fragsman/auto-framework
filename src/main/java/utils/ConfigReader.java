package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private final String propertyFilePath= "src/main/java/utils/driver-config.properties";

    public ConfigReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties failed to open. Path: " + propertyFilePath);
        }
    }

    public String getDriverPath(){
        String driverPath = properties.getProperty("driverPath");
        if(driverPath!= null)
            return driverPath;
        else
            throw new RuntimeException("Driver Path not found. Check driver-config.properties");
    }

    public long getImplicitWaitTime() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if(implicitlyWait != null) {
            try{
                return Long.parseLong(implicitlyWait);
            }catch(NumberFormatException e) {
                throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
            }
        }
        return 40;
    }

    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        if(browserName == null || browserName.equals("edge"))
            return DriverType.EDGE;
        else
        if(browserName.equalsIgnoreCase("firefox"))
            return DriverType.FIREFOX;
        else if(browserName.equals("chrome"))
            return DriverType.CHROME;
        else
            throw new RuntimeException("Invalid browser value in driver-config.properties: " + browserName);
    }

    public Boolean getBrowserWindowMaximized() {
        String windowSize = properties.getProperty("windowMaximize");
        if(windowSize != null)
            return Boolean.valueOf(windowSize);
        return true;
    }

    public String getDriverKey(){
        String driverPath = properties.getProperty("driverKey");
        if(driverPath!= null)
            return driverPath;
        else
            throw new RuntimeException("Driver Key not found. Check driver-config.properties");
    }
}
