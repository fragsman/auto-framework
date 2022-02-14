package utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class DriverManager {

    private static WebDriver driver;
    private static DriverType driverType;
    private static ConfigReader configReader;

    public static WebDriver getDriver() {
        if(driver==null) {
            configReader = new ConfigReader();
            driverType = configReader.getBrowser();
            createDriver();
        }
        return driver;
    }

    private static void createDriver() {
        switch (driverType) {
            case EDGE :
                //System.setProperty(configReader.getDriverKey(),configReader.getDriverPath());
                //We currently have the path set up on an Environment Variable. This is another way which will work too
                driver = new EdgeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configReader.getImplicitWaitTime()));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(configReader.getImplicitWaitTime()));
                break;
            case FIREFOX : //to-implement
                break;
            case CHROME : //to-implement
                break;
        }

        if(configReader.getBrowserWindowMaximized())
            driver.manage().window().maximize();
    }

    public static void closeDriver() {
        driver.close();
        driver.quit();
    }
}
