package POM;

import POJO.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Interactor;
import utils.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StorePage extends BasePage{

    private final By products = By.cssSelector(".products img");
    private final By productNames = By.cssSelector("a h2");
    private final By addToCartButtons = By.xpath("//a[contains(text(),'Add to cart')]");
    private final By currentPageLink = By.className("woocommerce-breadcrumb");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public String getCurrentPageInNav() {
    	return Interactor.findElement(driver, currentPageLink).getText().split("/")[1].trim();
    }
    
    public void clickOnFirstAvailableProduct(){
        List<WebElement> productLinks = Interactor.findElements(driver, products);
        productLinks.get(0).click();
    }

    private List<String> getProductNames(){
        List<WebElement> listOfProductNameContainers = Interactor.findElements(driver, productNames);
        List<String> listOfProductNames = new ArrayList<>();
        for (WebElement elem: listOfProductNameContainers) {
            listOfProductNames.add(elem.getText());
        }
        return listOfProductNames;
    }

    private List<Integer> getProductIds(){
        List<WebElement> listOfIdContainers = Interactor.findElements(driver, addToCartButtons);
        List<Integer> listOfIds = new ArrayList<>();
        for(WebElement elem : listOfIdContainers){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Integer id = Integer.valueOf((String)js.executeScript("return arguments[0].getAttribute('data-product_id')",elem));
            listOfIds.add(id);
        }
        return listOfIds;
    }

    public void createListOfProducts(){
        List<String> namesList = getProductNames();
        List<Integer> idList = getProductIds();
        List<Product> listOfProducts = new ArrayList<>();
        Iterator<String> itNames = namesList.iterator();
        Iterator<Integer> itIds = idList.iterator();
        while(itNames.hasNext() && itIds.hasNext()){
            Integer id = itIds.next();
            String name = itNames.next();
            Product product = new Product(id, name);
            listOfProducts.add(product);
        }
        for (Product p : listOfProducts){
            Logger.Info("Product Id: " + p.getId() + ", name: " + p.getName());
        }
    }
}

