package POM;

import org.openqa.selenium.By;

public class HeaderBar extends BasePage{
    private final By cartIcon = By.xpath("//div[@id='ast-desktop-header']//div[@class='ast-cart-menu-wrap']");

    public void enterToCart() {
        driver.findElement(cartIcon).click();
    }
}
