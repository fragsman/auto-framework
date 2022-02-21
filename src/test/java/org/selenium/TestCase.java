package org.selenium;

import POJO.BillingAddress;
import POM.*;
import org.testng.annotations.Test;
import utils.JacksonUtil;

public class TestCase extends BaseTest{

    @Test
    public void simpleTest() {
        BillingAddress billingAddress = JacksonUtil.deserializeJson("billingAddress.json", BillingAddress.class);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSuperiorLink("Store");

        StorePage storePage = new StorePage(driver);
        //storePage.createListOfProducts(); /** TESTING **/
        storePage.clickOnFirstAvailableProduct();

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        productDetailPage.clickAddToCartButton();

        HeaderBar headerBar = new HeaderBar(driver);
        headerBar.enterToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickOnProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.placeOrder();
    }
}
