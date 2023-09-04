package selenium;


import POM.BasePage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.DriverManager;
import utils.Logger;
import java.lang.reflect.*;

public class BaseTest{

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected DriverManager driverManager;
    private ITestContext context;
    private ThreadLocal<String> currentMethodName = new ThreadLocal<>();
    
    //I annotated below methods with alwaysRun=true, otherwise they will be ignored due to the current groups config in testng.xml
    
    @BeforeMethod(alwaysRun = true)
    public void navigateToWebsiteToTest(ITestContext context, Method method){
        Logger.Info("BeforeMethod: Creating driver, opening browser and navigating to homepage");
    	this.context = context;
    	currentMethodName.set(method.getName());
        driverManager = new DriverManager();
        driver.set(driverManager.getDriver());
        BasePage basePage = new BasePage(getDriver());
        basePage.navigateToMainPage();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
    	Logger.Info("BeforeMethod: Closing driver");
    	driver.get().close();
        driver.get().quit();
    }
    
    public WebDriver getDriver() {
        return driver.get();
    }
    
    public ITestContext getITestContext() {
    	return context;
    }
    
    public String getCurrentMethodName() {
    	return currentMethodName.get();
    }
}
