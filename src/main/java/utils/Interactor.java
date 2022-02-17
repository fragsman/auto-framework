package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public static WebElement findElement(WebDriver driver, By locator, int specificTimeout){
        WebElement element = null;
        try{
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(specificTimeout));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element = driver.findElement(locator);
        }catch(Exception e){
            System.out.println("Failed to find element: retrying in 500ms");
            //TODO: implement a retry mechanism after a delay of 500-1000ms
        }
        return element;
    }

    public static void javascriptClick(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click", element);
    }

    public static void scrollToElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollToView()", element);
    }

    public static void selectOption(WebDriver driver, WebElement element, String option) {
        //The implementation of this will depend on the website.
        // In general all select elements withing a same web-page will be exactly the same. So this should work for an entire web-page.
        WebElement inputCountry = findElement(driver, By.cssSelector("input.select2-search__field"));
        inputCountry.sendKeys(option);

        WebElement result = findElement(driver, By.cssSelector("span.select2-results ul li"));
        result.click();
    }

    public static void selectNthElement(WebDriver driver, WebElement element, int nthElement) {
    }

    public static void waitForBlockingOverlays(WebDriver driver, List<WebElement> blockingOverlays){
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(blockingOverlays));
    }
}
