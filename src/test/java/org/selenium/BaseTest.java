package org.selenium;

import POM.BasePage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.DriverManager;
import utils.Logger;

public class BaseTest{

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected DriverManager driverManager;
    
    @BeforeMethod
    public void navigateToWebsiteToTest(){
        Logger.Info("BeforeMethod: Creating driver, opening browser and navigating to homepage");
        driverManager = new DriverManager();
        driver.set(driverManager.getDriver());
        BasePage basePage = new BasePage(getDriver());
        basePage.navigateToMainPage();
    }

    @AfterMethod
    public void closeBrowser(){
    	Logger.Info("BeforeMethod: Closing driver");
    	driver.get().close();
        driver.get().quit();
    }
    
    public WebDriver getDriver() {
        return driver.get();
    }
}
