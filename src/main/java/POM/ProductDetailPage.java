package POM;

import org.openqa.selenium.By;

public class ProductDetailPage extends BasePage{
    private final By addToCartBtn = By.name("add-to-cart");

    public void clickAddToCartButton(){
        driver.findElement(addToCartBtn).click();
    }
}