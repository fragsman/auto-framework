package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Interactor;

public class ProductDetailPage extends BasePage{
    private final By addToCartBtn = By.name("add-to-cart");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCartButton() {
        HeaderBar headerBar = new HeaderBar(driver);
        int prevItemsInCart = headerBar.getCurrentItemsInCart();
        Interactor.findElement(driver, addToCartBtn).click();
        int currentItemsInCart = headerBar.getCurrentItemsInCart();
        int retry = 0;
        while(prevItemsInCart == currentItemsInCart && retry <= 10){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            currentItemsInCart = headerBar.getCurrentItemsInCart();
            retry++;
        }
    }
}