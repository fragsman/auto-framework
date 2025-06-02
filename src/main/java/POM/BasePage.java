package POM;

import org.openqa.selenium.WebDriver;
import utils.EnvironmentManager;
import utils.Interactor;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToMainPage(){
        driver.get(EnvironmentManager.getInstance().getBaseUrl());
    }

    public void waitForBlockingOverlays(){
        Interactor.waitForBlockingOverlays(driver);
    }
}
