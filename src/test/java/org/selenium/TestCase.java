package org.selenium;

import POJO.BillingAddress;
import POM.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JacksonUtil;

public class TestCase extends BaseTest{

    @Test
    public void buyAnyProductTest() {
        BillingAddress billingAddress = JacksonUtil.deserializeJson("billingAddress.json", BillingAddress.class);

        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickOnSuperiorLink("Store");

        StorePage storePage = new StorePage(getDriver());
        //storePage.createListOfProducts(); /** TESTING **/
        storePage.clickOnFirstAvailableProduct();

        ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
        productDetailPage.clickAddToCartButton();

        HeaderBar headerBar = new HeaderBar(getDriver());
        headerBar.enterToCart();

        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickOnProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.placeOrder();

        CheckoutResults checkoutResults = new CheckoutResults(getDriver());
        Assert.assertEquals(checkoutResults.getCheckoutNotice(),"Thank you. Your order has been received.");
    }

    @Test
    public void enterInvalidCouponTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickOnSuperiorLink("Store");

        StorePage storePage = new StorePage(getDriver());
        storePage.clickOnFirstAvailableProduct();

        ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
        productDetailPage.clickAddToCartButton();

        HeaderBar headerBar = new HeaderBar(getDriver());
        headerBar.enterToCart();

        CartPage cartPage = new CartPage(getDriver());
        cartPage.enterCouponCode("invalid");

        Assert.assertEquals(cartPage.getCouponErrorResult(),"Coupon \"invalid\" does not exist!");
    }
}
