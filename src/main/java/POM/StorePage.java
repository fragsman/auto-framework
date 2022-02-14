package POM;

import org.openqa.selenium.By;

public class StorePage extends BasePage{

    private final String products = ".products img";

    public void clickOnFirstAvailableProduct(){
        driver.findElements(By.cssSelector(products)).get(0).click();
    }
}

