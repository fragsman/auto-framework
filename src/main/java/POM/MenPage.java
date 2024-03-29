package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Interactor;

public class MenPage extends BasePage{
    
	private final By currentPageLink = By.className("woocommerce-breadcrumb");
	
	public MenPage(WebDriver driver) {
        super(driver);
    }
	
    public String getCurrentPageInNav() {
    	return Interactor.findElement(driver, currentPageLink).getText().split("/")[1].trim();
    }
}
