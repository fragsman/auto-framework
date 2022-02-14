package POM;

import org.openqa.selenium.By;
import utils.Interactor;

public class ProductDetailPage extends BasePage{
    private final By addToCartBtn = By.name("add-to-cart");

    public void clickAddToCartButton() {
        HeaderBar headerBar = new HeaderBar();
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