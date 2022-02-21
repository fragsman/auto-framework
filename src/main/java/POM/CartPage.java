package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Interactor;

public class CartPage extends BasePage{
    private final By proceedToCheckoutBtn = By.cssSelector("div.wc-proceed-to-checkout > a");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnProceedToCheckout(){
        Interactor.findElement(driver, proceedToCheckoutBtn).click();
    }
}
