package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;

public class DriverManager {

    private DriverType driverType;

    public WebDriver getDriver() {
        driverType = ConfigReader.getInstance().getBrowser();
        WebDriver driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        WebDriver driver = null;
        switch (driverType) {
            case EDGE :
                //System.setProperty(configReader.getDriverKey(),configReader.getDriverPath());
                //We currently have the path set up on an Environment Variable. This is another way which will work too
                driver = new EdgeDriver();
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getInstance().getImplicitWaitTime()));
                Logger.Info("Edge Driver created!");
                break;
            case FIREFOX : //to-implement
                break;
            case CHROME : //to-implement
                break;
        }

        if(ConfigReader.getInstance().getBrowserWindowMaximized())
            driver.manage().window().maximize();

        return driver;
    }
}
