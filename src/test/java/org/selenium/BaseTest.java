package org.selenium;

import POM.BasePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.DriverManager;

public class BaseTest {

    @BeforeTest
    public void navigateToWebsiteToTest(){
        BasePage basePage = new BasePage();
        basePage.navigateToMainPage();
    }

    @AfterTest
    public void closeBrowser(){
        DriverManager.closeDriver();
    }
}
