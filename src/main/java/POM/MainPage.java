package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Interactor;
import utils.Logger;

public class MainPage extends BasePage{

    private final By loc_storeLink = By.xpath("//li[@id='menu-item-1227']/a");
    private final By loc_menLink = By.xpath("//li[@id='menu-item-1228']/a");
    private final By loc_womenLink = By.xpath("//li[@id='menu-item-1229']/a");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSuperiorLink(String name) {
        WebElement elem;
        switch(name){
            case "Store": elem = Interactor.findElement(driver,loc_storeLink); break;
            case "Men": elem = driver.findElement(loc_menLink); break;
            case "Women": elem = driver.findElement(loc_womenLink); break;
            default: elem = null;
        }
        if(elem==null)
            Logger.Info("clickOnSuperiorLink: " + name + ", not a valid option");
        else
            elem.click();
    }
}
