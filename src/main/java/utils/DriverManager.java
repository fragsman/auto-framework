package utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    public WebDriver getDriver() {
    	DriverType driverType = DriverConfigurator.getInstance().getBrowser();
        WebDriver driver = createDriver(driverType);
        return driver;
    }

    private WebDriver createDriver(DriverType driverType) {
        WebDriver driver = null;
    	
        switch (driverType) {
            case EDGE :
            	driver = DriverConfigurator.getInstance().configureEdge();
                Logger.Info("Edge Driver created!");
                break;
            case FIREFOX : //to-implement
                break;
            case CHROME : //to-implement
                break;
        }
        
        return driver;
    }
}
