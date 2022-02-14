package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Interactor;

import java.util.List;

public class StorePage extends BasePage{

    private final By products = By.cssSelector(".products img");

    public void clickOnFirstAvailableProduct(){
        List<WebElement> productLinks = Interactor.findElements(driver, products);
        productLinks.get(0).click();
    }
}

