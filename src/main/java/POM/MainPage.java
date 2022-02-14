package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage{

    private final By loc_storeLink = By.xpath("//li[@id='menu-item-1227']/a");

    public MainPage(){
        super();
    }

    public void clickOnSuperiorLink(String name) {
        WebElement elem;
        switch(name){
            case "Store": elem = driver.findElement(loc_storeLink); break;
            default: elem = null;
        }
        if(elem==null)
            System.out.println("clickOnSuperiorLink: "+name+", not a valid option");
        else
            elem.click();
    }
}
