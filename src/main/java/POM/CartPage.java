package POM;

import org.openqa.selenium.By;

public class CartPage extends BasePage{
    private final By proceedToCheckoutBtn = By.cssSelector("div.wc-proceed-to-checkout > a");

    public void clickOnProceedToCheckout(){
        driver.findElement(proceedToCheckoutBtn).click();
    }
}
