package POM;

import POJO.BillingAddress;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage{

    private final By firstNameInput = By.cssSelector("#billing_first_name");
    private final By lastNameInput = By.cssSelector("#billing_last_name");
    private final By addressLine1Input = By.name("billing_address_1");
    private final By cityInput = By.name("billing_city");
    private final By postalCodeInput = By.name("billing_postcode");
    private final By emailInput = By.name("billing_email");

    public void setBillingAddress(BillingAddress ba){
        enterFirstName(ba.getFirstName());
        enterLastName(ba.getLastName());
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
}
