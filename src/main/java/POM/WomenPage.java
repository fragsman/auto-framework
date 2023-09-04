package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Interactor;

public class WomenPage extends BasePage {

	private final By currentPageLink = By.className("woocommerce-breadcrumb");
	private final By title = By.tagName("h1");
	
	public WomenPage(WebDriver driver) {
        super(driver);
    }
	
    public String getCurrentPageInNav() {
    	return Interactor.findElement(driver, currentPageLink).getText().split("/")[1].trim();
    }
    
    public String getPageTitle() {
    	return Interactor.findElement(driver, title).getText();
    }
}
