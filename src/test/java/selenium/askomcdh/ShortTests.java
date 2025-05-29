package selenium.askomcdh;

import POM.*;
import selenium.BaseTest;
import support.MyAssert;
import support.MySoftAssert;

import org.testng.annotations.Test;

public class ShortTests extends BaseTest{
    
    @Test(groups = {"smoke"})
    public void storeLinkLeadsToStorePage() {
    	MainPage mainPage = new MainPage(getDriver());
    	mainPage.clickOnSuperiorLink("Store");
        StorePage storePage = new StorePage(getDriver());
        
        //I will make it fail on purpose just to see a failure on the report
        MyAssert ma = new MyAssert(getITestContext(),getCurrentMethodName());
        ma.assertEq(storePage.getCurrentPageInNav(), "StoreXXX", "Check that Store link leads to a page that contains 'StoreXXX' on its navigation tree");
    }
    
    @Test(groups = {"smoke"})
    public void menLinkLeadsToMenPage() {
    	MainPage mainPage = new MainPage(getDriver());
    	mainPage.clickOnSuperiorLink("Men");
        MenPage menPage = new MenPage(getDriver());
        
        MyAssert ma = new MyAssert(getITestContext(),getCurrentMethodName());
        ma.assertEq(menPage.getCurrentPageInNav(), "Men", "Check that Men link leads to a page that contains 'Men' on its navigation tree");
    }

    @Test(groups = {"smoke"})
    public void seleniumActionsTest() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickOnSuperiorLink("Men");
        MenPage menPage = new MenPage(getDriver());
        menPage.specialSearch();

        MyAssert ma = new MyAssert(getITestContext(),getCurrentMethodName());
        ma.assertEq(menPage.getSearchResultsTitle(), "Search results: \u201CShoes\u201d", "Verify the first letter is capital");
    }
    
    @Test(groups = {"smoke"})
    public void womenLinkLeadsToWomenPage() {
    	MainPage mainPage = new MainPage(getDriver());
    	mainPage.clickOnSuperiorLink("Women");
        WomenPage womenPage = new WomenPage(getDriver());

        MySoftAssert msa = new MySoftAssert(getITestContext(),getCurrentMethodName());
        //I make one of the SoftAssert fail just to see that it will report both Pass and Fail messages
        msa.assertEquals(womenPage.getPageTitle(), "W1men", "Check that Women page have 'Women' on its title");
        msa.assertEquals(womenPage.getCurrentPageInNav(),"Women","Check that Women link leads to a page that contains 'Women' on its navigation tree");
        msa.assertAll();
    }
}
