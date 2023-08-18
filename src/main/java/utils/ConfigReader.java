package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader configReader;
    private static Properties properties;

    private ConfigReader(){
        BufferedReader reader;
        String propertyFilePath = getPropertiesFilePath();
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

    private String getPropertiesFilePath(){
        //the env property can be injected by maven command line or depend on CI-CD tool configuration.
        String env = System.getProperty("env",String.valueOf(EnvType.QA));
        String filePath;
        switch(env){
            case "DEV": filePath = "src/main/java/properties/dev-config.properties"; break;
            case "QA": filePath = "src/main/java/properties/qa-config.properties"; break;
            case "STG": filePath = "src/main/java/properties/stg-config.properties"; break;
            default: throw new RuntimeException("Environment does not exist");
        }
        return filePath;
    }

    public static ConfigReader getInstance(){
        if(configReader==null)
            configReader = new ConfigReader();
        return configReader;
    }

    public static String getDriverPath(){
        String driverPath = properties.getProperty("driverPath");
        if(driverPath!= null)
            return driverPath;
        else
            throw new RuntimeException("Driver Path not found. Check config.properties");
    }

    public static long getImplicitWaitTime() {
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

    public static String getDriverKey(){
        String driverPath = properties.getProperty("driverKey");
        if(driverPath!= null)
            return driverPath;
        else
            throw new RuntimeException("Driver Key not found. Check config.properties");
    }

    public String getBaseUrl() {
        String baseUrl = properties.getProperty("baseUrl");
        if (baseUrl != null)
            return baseUrl;
        else
            throw new RuntimeException("baseUrl not found. Check config.properties");
    }
}
