package utils;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverManager {

    public WebDriver getDriver() {
    	DriverType driverType = EnvironmentManager.getInstance().getBrowser();
        WebDriver driver = createDriver(driverType);
        return driver;
    }

    private WebDriver createDriver(DriverType driverType) {
        WebDriver driver = null;
    	
        switch (driverType) {
            case EDGE :
            	driver = configureEdge();
                Logger.Info("Edge Driver created!");
                break;
            case FIREFOX : //to-implement
                break;
            case CHROME : //to-implement
                break;
        }
        
        return driver;
    }
    
    public WebDriver configureEdge() {
    	WebDriver driver = null;
    	EnvironmentManager eMgr = EnvironmentManager.getInstance();
    	System.setProperty(eMgr.getDriverKey(),eMgr.getDriverPath());
    	//Another option is to have the path set up on an Environment Variable.
    	
    	EdgeOptions options = new EdgeOptions();
    	options.setPageLoadStrategy(PageLoadStrategy.EAGER);
    	options.setPageLoadTimeout(Duration.ofSeconds(eMgr.getImplicitlyWait()));
        options.setImplicitWaitTimeout(Duration.ofSeconds(eMgr.getImplicitlyWait()));
    	if(eMgr.isHeadless())
    		options.addArguments("--headless=chrome");
    	driver = new EdgeDriver(options);
        
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(eMgr.getImplicitlyWait()));
        
        if(eMgr.isHeadless()) //On headless mode we cannot maximize so we need to set the viewport resolution
        	driver.manage().window().setSize(new Dimension(eMgr.getViewportWidth(), eMgr.getViewportHeight()));
       
        if(eMgr.isWindowMaximize())
            driver.manage().window().maximize();
        return driver;
    }
}
