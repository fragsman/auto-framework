package org.selenium;

import POJO.BillingAddress;
import POM.*;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import utils.JacksonUtil;

public class TestCase extends BaseTest{

    @Test @Ignore
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

    @Test @Ignore
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
    
    @Test
    public void storeLinkLeadsToStorePage() {
    	MainPage mainPage = new MainPage(getDriver());
    	mainPage.clickOnSuperiorLink("Store");

        StorePage storePage = new StorePage(getDriver());
        //I will make it fail on purpose just to see a failure on the report
        Assert.assertEquals("StoreXXX", storePage.getCurrentPageInNav(),"Store link doesn't lead to the expected destination");
    }
    
    @Test
    public void menLinkLeadsToMenPage() {
    	MainPage mainPage = new MainPage(getDriver());
    	mainPage.clickOnSuperiorLink("Men");

        MenPage menPage = new MenPage(getDriver());
        Assert.assertEquals("Men", menPage.getCurrentPageInNav(),"Men link doesn't lead to the expected destination");
    }
    
    @Test
    public void womenLinkLeadsToWomenPage() {
    	MainPage mainPage = new MainPage(getDriver());
    	mainPage.clickOnSuperiorLink("Women");

        WomenPage womenPage = new WomenPage(getDriver());
        Assert.assertEquals("Women", womenPage.getCurrentPageInNav(),"Women link doesn't lead to the expected destination");
    }
}
