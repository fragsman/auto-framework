package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Interactor;

public class CartPage extends BasePage{
    private final By proceedToCheckoutBtn = By.cssSelector("div.wc-proceed-to-checkout > a");
    private final By couponCodeInput = By.id("coupon_code");
    private final By couponCodeBtn = By.name("apply_coupon");
    private final By couponErrorMsg = By.cssSelector("ul.woocommerce-error li");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnProceedToCheckout(){
        Interactor.findElement(driver, proceedToCheckoutBtn).click();
    }

    public void enterCouponCode(String code){
        Interactor.findElement(driver, couponCodeInput).sendKeys(code);
        Interactor.findElement(driver, couponCodeBtn).click();
        waitForBlockingOverlays();
    }

    public String getCouponErrorResult(){
        return Interactor.findElement(driver, couponErrorMsg).getText();
    }
}
