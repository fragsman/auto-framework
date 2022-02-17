package POM;

import POJO.BillingAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Interactor;

public class CheckoutPage extends BasePage{

    private final By firstNameInput = By.cssSelector("#billing_first_name");
    private final By lastNameInput = By.cssSelector("#billing_last_name");
    private final By addressLine1Input = By.name("billing_address_1");
    private final By cityInput = By.name("billing_city");
    private final By postalCodeInput = By.name("billing_postcode");
    private final By emailInput = By.name("billing_email");
    private final By placeOrderBtn = By.name("woocomerce_checkout_place_order");
    private final By selectCountry = By.cssSelector("span.select2-selection span");

    public void setBillingAddress(BillingAddress ba){
        enterFirstName(ba.getFirstName());
        enterLastName(ba.getLastName());
        enterCountry(ba.getCountry());
        enterAddressLine1(ba.getAddressLineOne());
        enterCity(ba.getCity());
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
    }

    public void enterCity(String city){
        driver.findElement(cityInput).sendKeys(city);
    }

    public void enterPostalCode(String postalCode){
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    public void enterEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void placeOrder(){
        Interactor.findElement(driver, placeOrderBtn).click();
    }

    public void enterCountry(String country){
        WebElement selectCountrySelector = Interactor.findElement(driver, selectCountry);
        selectCountrySelector.click();
        Interactor.selectOption(driver, selectCountrySelector, country);
        waitForBlockingOverlays();
    }
}
