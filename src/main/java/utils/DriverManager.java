package utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

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
            	System.setProperty(ConfigReader.getDriverKey(),ConfigReader.getDriverPath());
                //Another option is to have the path set up on an Environment Variable.
                
            	EdgeOptions options = new EdgeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                options.setPageLoadTimeout(Duration.ofSeconds(ConfigReader.getImplicitWaitTime()));
                options.setImplicitWaitTimeout(Duration.ofSeconds(ConfigReader.getImplicitWaitTime()));
                //The line below solves the Connection Reset errors. Details: https://bugs.chromium.org/p/chromedriver/issues/detail?id=3689&q=websocket&can=2
                //options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir"));
                driver = new EdgeDriver(options);
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigReader.getImplicitWaitTime()));
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
