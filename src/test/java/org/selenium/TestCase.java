package org.selenium;

import POJO.BillingAddress;
import POM.*;
import org.testng.annotations.Test;
import utils.JacksonUtil;

import java.io.InputStream;

public class TestCase extends BaseTest{

    @Test
    public void simpleTest() {
        BillingAddress billingAddress = new BillingAddress();
        InputStream is = getClass().getClassLoader().getResourceAsStream("billingAddress.json");
        billingAddress = JacksonUtil.deserializeJson(is, billingAddress);

        MainPage mainPage = new MainPage();
        mainPage.clickOnSuperiorLink("Store");

        StorePage storePage = new StorePage();
        storePage.clickOnFirstAvailableProduct();

        ProductDetailPage productDetailPage = new ProductDetailPage();
        productDetailPage.clickAddToCartButton();

        HeaderBar headerBar = new HeaderBar();
        headerBar.enterToCart();

        CartPage cartPage = new CartPage();
        cartPage.clickOnProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.placeOrder();
    }
}
