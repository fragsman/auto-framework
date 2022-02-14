package POM;

import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class BasePage {

    protected static WebDriver driver;

    public BasePage(){
        driver = DriverManager.getDriver();
    }

    public void navigateToMainPage(){
        driver.get("https://askomdch.com/");
    }
}
