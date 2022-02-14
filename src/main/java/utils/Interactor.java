package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Interactor {

    public static void clickThis(WebElement element, WebDriver driver){
        try{
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            element.click();
        }catch(Exception e){
            System.out.println("Failed to click element: retrying in 500ms");
            //retryClick(element);
        }
    }

    public static List<WebElement> findElements(WebDriver driver, By locator){
        List<WebElement> elements = new ArrayList<WebElement>();
        try{
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            elements = driver.findElements(locator);
        }catch(Exception e){
            System.out.println("Failed to find elements: retrying in 500ms");
            //TODO: implement a retry mechanism after a delay of 500-1000ms
        }
        return elements;
    }

    public static WebElement findElement(WebDriver driver, By locator){
        WebElement element = null;
        try{
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element = driver.findElement(locator);
        }catch(Exception e){
            System.out.println("Failed to find element: retrying in 500ms");
            //TODO: implement a retry mechanism after a delay of 500-1000ms
        }
        return element;
    }
}
