package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Interactor;

public class CheckoutResults extends BasePage{

    private final By checkoutNotice = By.cssSelector("p.woocommerce-notice");

    public CheckoutResults(WebDriver driver){
        super(driver);
    }

    public String getCheckoutNotice(){
        return Interactor.findElement(driver, checkoutNotice).getText();
    }
}
