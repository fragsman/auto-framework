package POM;

import org.openqa.selenium.By;
import utils.Interactor;

public class HeaderBar extends BasePage{
    private final By cartIcon = By.xpath("//div[@id='ast-desktop-header']//div[@class='ast-cart-menu-wrap']");
    private final By cartIconCount = By.xpath("//div[@id='ast-desktop-header']//div[@class='ast-cart-menu-wrap']/span");

    public void enterToCart() {
        Interactor.findElement(driver, cartIcon).click();
    }

    public int getCurrentItemsInCart(){
        String cartText = Interactor.findElement(driver, cartIconCount).getText();
        cartText.replace(" ","");
        return Integer.parseInt(cartText);
    }
}
