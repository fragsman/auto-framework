package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;

public class DriverManager {

    private WebDriver driver;
    private DriverType driverType;
    private ConfigReader configReader;

    public WebDriver getDriver() {
        if(driver==null) {
            configReader = new ConfigReader();
            driverType = configReader.getBrowser();
            createDriver();
        }
        return driver;
    }

    private void createDriver() {
        switch (driverType) {
            case EDGE :
                //System.setProperty(configReader.getDriverKey(),configReader.getDriverPath());
                //We currently have the path set up on an Environment Variable. This is another way which will work too
                driver = new EdgeDriver();
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(configReader.getImplicitWaitTime()));
                Logger.Info("Edge Driver created!");
                break;
            case FIREFOX : //to-implement
                break;
            case CHROME : //to-implement
                break;
        }

        if(configReader.getBrowserWindowMaximized())
            driver.manage().window().maximize();
    }

    public void closeDriver() {
        Logger.Info("Edge Driver closed!");
        driver.close();
        driver.quit();
    }
}
