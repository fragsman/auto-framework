package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import POJO.test.BillingAddress;
import utils.Interactor;

public class CheckoutPage extends BasePage{

    private final By firstNameInput = By.cssSelector("#billing_first_name");
    private final By lastNameInput = By.cssSelector("#billing_last_name");
    private final By addressLine1Input = By.name("billing_address_1");
    private final By cityInput = By.name("billing_city");
    private final By postalCodeInput = By.name("billing_postcode");
    private final By emailInput = By.name("billing_email");
    private final By placeOrderBtn = By.cssSelector("#place_order");
    private final By countrySelect = By.cssSelector("span.select2-selection span");
    private final By countrySelectResults = By.cssSelector("span.select2-results");
    private final By stateSelect = By.id("select2-billing_state-container");

    public CheckoutPage(WebDriver driver){
        super(driver);
        waitForBlockingOverlays();
    }

    public void setBillingAddress(BillingAddress ba){
        enterFirstName(ba.getFirstName());
        enterLastName(ba.getLastName());
        enterCountry(ba.getCountry());
        enterAddressLine1(ba.getAddressLineOne());
        enterCity(ba.getCity());
        enterState(ba.getState());
        enterPostalCode(ba.getPostalCode());
        enterEmail(ba.getEmail());
    }

    public void enterFirstName(String firstName){
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterAddressLine1(String addressLine1){
        driver.findElement(addressLine1Input).sendKeys(addressLine1);
        waitForBlockingOverlays();
    }

    public void enterCity(String city){
        driver.findElement(cityInput).sendKeys(city);
        waitForBlockingOverlays();
    }

    public void enterPostalCode(String postalCode){
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        waitForBlockingOverlays();
    }

    public void enterEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void placeOrder(){
        WebElement checkoutBtn = Interactor.findElement(driver, placeOrderBtn);
        Interactor.accurateClick(driver, checkoutBtn);
    }

    public void enterCountry(String country){
        WebElement selectCountrySelector = Interactor.findElement(driver, countrySelect);
        selectCountrySelector.click();
        Interactor.selectOption(driver, selectCountrySelector, country);
        waitForBlockingOverlays();
    }

    public void enterState(String stateText){
        WebElement selectStateSelector = Interactor.findElement(driver,stateSelect);
        selectStateSelector.click();
        Interactor.selectOption(driver,selectStateSelector, stateText);
        waitForBlockingOverlays();
    }

    public void selectNthCountry(int countryPosition){
        WebElement selectCountrySelector = Interactor.findElement(driver,countrySelect);
        selectCountrySelector.click();
        WebElement selectCountryResults = Interactor.findElement(driver, countrySelectResults);
        Interactor.selectNthElement(selectCountryResults,countryPosition);
    }

    public String getCurrentSelectedCountry(){
        return Interactor.findElement(driver,countrySelect).getText();
    }
}
