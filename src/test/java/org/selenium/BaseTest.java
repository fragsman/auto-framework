package org.selenium;

import POM.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.DriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected DriverManager driverManager;

    @BeforeTest
    public void navigateToWebsiteToTest(){
        driverManager = new DriverManager();
        driver = driverManager.getDriver();
        BasePage basePage = new BasePage(driver);
        basePage.navigateToMainPage();
    }

    @AfterTest
    public void closeBrowser(){
        driverManager.closeDriver();
    }
}
