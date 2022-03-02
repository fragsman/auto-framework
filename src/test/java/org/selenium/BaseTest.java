package org.selenium;

import POM.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;

public class BaseTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected DriverManager driverManager;

    @BeforeMethod
    public void navigateToWebsiteToTest(){
        driverManager = new DriverManager();
        setDriver(driverManager.getDriver());
        BasePage basePage = new BasePage(getDriver());
        basePage.navigateToMainPage();
    }

    @AfterMethod
    public void closeBrowser(){
        driver.get().quit();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }
}
