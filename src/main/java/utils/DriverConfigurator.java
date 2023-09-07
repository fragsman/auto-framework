package utils;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import POJO.cfg.EnvironmentConfiguration;

public class DriverConfigurator {

	private static DriverConfigurator configurator;
	private static EnvironmentConfiguration eCfg;

    private DriverConfigurator(){
        String cfgFilePath = getConfigFilePath();
        eCfg = JacksonUtil.deserializeJson(cfgFilePath, EnvironmentConfiguration.class);
    }
    
    private String getConfigFilePath(){
        //The env property can be injected by maven command line or depend on CI-CD tool config. If none, QA is default.
        String env = System.getProperty("env",String.valueOf(EnvType.QA));
        String cfgFilePath;
        switch(env){
            case "DEV": cfgFilePath = "env-cfg/dev-config.json"; break;
            case "QA": cfgFilePath = "env-cfg/qa-config.json"; break;
            case "STG": cfgFilePath = "env-cfg/stg-config.json"; break;
            case "GITHUB": cfgFilePath = "env-cfg/github-config.json"; break;
            default: throw new RuntimeException("Environment does not exist");
        }
        return cfgFilePath;
    }
    
    //Singleton implemented to avoid reading the file every time a test runs
    public static DriverConfigurator getInstance(){
        if(configurator == null)
        	configurator = new DriverConfigurator();
        return configurator;
    }
    
    public WebDriver configureEdge() {
    	WebDriver driver = null;
    	System.setProperty(eCfg.getDriverKey(),eCfg.getDriverPath());
    	//Another option is to have the path set up on an Environment Variable.
    	
    	EdgeOptions options = new EdgeOptions();
    	options.setPageLoadStrategy(PageLoadStrategy.EAGER);
    	options.setPageLoadTimeout(Duration.ofSeconds(eCfg.getImplicitlyWait()));
    	options.setImplicitWaitTimeout(Duration.ofSeconds(eCfg.getImplicitlyWait()));
    	if(eCfg.isHeadless())
    		options.addArguments("--headless=chrome");
    	driver = new EdgeDriver(options);
        
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(eCfg.getImplicitlyWait()));
        
        if(eCfg.isHeadless()) //On headless mode we cannot maximize so we need to set the viewport resolution
        	driver.manage().window().setSize(new Dimension(eCfg.getHeadlessOptions().getViewportWidth(), eCfg.getHeadlessOptions().getViewportHeight()));
       
        if(eCfg.isWindowMaximize())
            driver.manage().window().maximize();
        return driver;
    }
    
    public DriverType getBrowser() {
        String browserName = eCfg.getBrowser();
        if(browserName.equals("edge"))
            return DriverType.EDGE;
        else
        	if(browserName.equalsIgnoreCase("firefox"))
        		return DriverType.FIREFOX;
        	else if(browserName.equals("chrome"))
        		return DriverType.CHROME;
        	else
        		throw new RuntimeException("Invalid browser value in config files: " + browserName);
    }
    
    public String getBaseUrl() {
    	return eCfg.getBaseUrl();
    }
}

