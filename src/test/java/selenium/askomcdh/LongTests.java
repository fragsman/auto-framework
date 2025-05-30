package selenium.askomcdh;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import POJO.test.BillingAddress;
import POM.CartPage;
import POM.CheckoutPage;
import POM.CheckoutResults;
import POM.HeaderBar;
import POM.MainPage;
import POM.ProductDetailPage;
import POM.StorePage;
import selenium.BaseTest;
import support.MyAssert;
import utils.JacksonUtil;

public class LongTests extends BaseTest {

	@Test(groups = {"regression"})
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
        MyAssert ma = new MyAssert(getITestContext(),getCurrentMethodName());
        ma.assertEq(checkoutResults.getCheckoutNotice(),"Thank you. Your order has been received.","Check that checkout message is correct");
    }

	@Test(groups = {"regression"})
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

        MyAssert ma = new MyAssert(getITestContext(), getCurrentMethodName());
        ma.assertEq(cartPage.getCouponErrorResult(), "Coupon \"invalid\" does not exist!", "Check that couppon doesn't exist message appears");
    }

    @Test(groups = {"regression"})
    public void selectTheThirdOptionInCheckout() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickOnSuperiorLink("Store");

        StorePage storePage = new StorePage(getDriver());
        storePage.clickOnFirstAvailableProduct();

        ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
        productDetailPage.clickAddToCartButton();

        HeaderBar headerBar = new HeaderBar(getDriver());
        headerBar.enterToCart();

        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickOnProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.selectNthCountry(3);
        String currentCountry = checkoutPage.getCurrentSelectedCountry();

        MyAssert ma = new MyAssert(getITestContext(), getCurrentMethodName());
        ma.assertEq(currentCountry, "Algeria", "Expected selected country does not match");
    }

    @Test(groups = {"regression"}) @Ignore
    public void thisIsHowYouIgnoreATest(){

    }
}
