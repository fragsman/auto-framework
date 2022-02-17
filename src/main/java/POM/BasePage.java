package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverManager;
import utils.Interactor;
import java.util.List;

public class BasePage {

    protected static WebDriver driver;
    protected final By overlayBlockers = By.cssSelector("div.blockUI.blockOverlay");

    public BasePage(){
        driver = DriverManager.getDriver();
    }

    public void navigateToMainPage(){
        driver.get("https://askomdch.com/");
    }

    public void waitForBlockingOverlays(){
        List<WebElement> blockingOverlays = Interactor.findElements(driver,overlayBlockers);
        Interactor.waitForBlockingOverlays(driver, blockingOverlays);
    }
}
