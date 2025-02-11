package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Interactor;

import javax.swing.*;

public class MenPage extends BasePage{
    
	private final By currentPageLink = By.className("woocommerce-breadcrumb");
    private final By searchResultsTitle = By.cssSelector("header h1");
    private final By searchButton = By.xpath("//button[contains(text(),'Search')]");
    private final By searchBox = By.xpath("//input[@type='search']");
	
	public MenPage(WebDriver driver) {
        super(driver);
    }
	
    public String getCurrentPageInNav() {
    	return Interactor.findElement(driver, currentPageLink).getText().split("/")[1].trim();
    }

    //This method doesn't make much sense, its only purpose is to quick use Selenium Actions
    public void specialSearch(){
        WebElement searchBoxElem = Interactor.findElement(driver, searchBox);
        Actions actions = new Actions(driver);
        actions.click(searchBoxElem);
        actions.keyDown(Keys.SHIFT);
        actions.sendKeys("s");
        actions.keyUp(Keys.SHIFT);
        actions.sendKeys("hoes");
        actions.perform();
        search();
    }

    public String getSearchResultsTitle(){
        return Interactor.findElement(driver, searchResultsTitle).getText();
    }

    public void search(){
        Interactor.findElement(driver, searchButton).click();
    }
}
