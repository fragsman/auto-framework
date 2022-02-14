package POM;

import org.openqa.selenium.By;
import utils.Interactor;

public class CartPage extends BasePage{
    private final By proceedToCheckoutBtn = By.cssSelector("div.wc-proceed-to-checkout > a");

    public void clickOnProceedToCheckout(){
        Interactor.findElement(driver, proceedToCheckoutBtn).click();
    }
}
