package utils;

import POJO.cfg.EnvironmentConfiguration;

public class EnvironmentManager {

	private static EnvironmentManager envManager;
	private EnvironmentConfiguration envCfg;

    private EnvironmentManager(){
        String cfgFilePath = getConfigFilePath();
        envCfg = JacksonUtil.deserializeJson(cfgFilePath, EnvironmentConfiguration.class);
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
    public static EnvironmentManager getInstance(){
        if(envManager == null)
        	envManager = new EnvironmentManager();
        return envManager;
    }
    
    public DriverType getBrowser() {
        String browserName = envCfg.getBrowser();
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
    	return envCfg.getBaseUrl();
    }
    
    public String getDriverKey() {
    	return envCfg.getDriverKey();
    }
    
    public String getDriverPath() {
    	return envCfg.getDriverPath();
    }
    
    public int getImplicitlyWait() {
    	return envCfg.getImplicitlyWait();
    }
    
    public boolean isHeadless() {
    	return envCfg.isHeadless();
    }
    
    public boolean isWindowMaximize() {
    	return envCfg.isWindowMaximize();
    }
    
    public int getViewportWidth() {
    	return envCfg.getHeadlessOptions().getViewportWidth();
    }
    
    public int getViewportHeight() {
    	return envCfg.getHeadlessOptions().getViewportHeight();
    }
}

