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

    private static int shortRetryTimeDelay = 1000;
    private static int mediumRetryTimeDelay = 2000;
    private static int longRetryTimeDelay = 3000;

    //Click that waits for element to be clickable
    public static void accurateClick(WebDriver driver, WebElement element){
        try{
            log("accurateClick",driver,getWebElementLocator(element));
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch(Exception e){
            Logger.Error("Failed to click element" + element.toString() + ": retrying");
            retryAccurateClick(driver,element);
        }
    }

    //A retry click after a delay
    private static void retryAccurateClick(WebDriver driver, WebElement element){
        try{
            log("retryAccurateClick",driver,getWebElementLocator(element));
            Thread.sleep(shortRetryTimeDelay); //This is a retry. It has previously failed, so we wait.
            scrollToElement(driver, element);
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch(Exception e){
            Logger.Error("Failed to retry click element" + element.toString());
        }
    }

    //Find multiple elements
    public static List<WebElement> findElements(WebDriver driver, By locator){
        List<WebElement> elements = new ArrayList<WebElement>();
        try{
            log("findElements",driver,locator.toString());
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            elements = driver.findElements(locator);
        }catch(Exception e){
            Logger.Error("Failed to find elements "+locator.toString()+": retrying");
            retryFindElements(driver, locator);
        }
        return elements;
    }

    //Find multiple elements with a specific timeout
    public static List<WebElement> findElements(WebDriver driver, By locator, int timeout){
        List<WebElement> elements = new ArrayList<WebElement>();
        try{
            log("findElements",driver,locator.toString());
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            elements = driver.findElements(locator);
        }catch(Exception e){
            Logger.Error("Failed to find elements "+locator.toString()+": retrying");
            retryFindElements(driver, locator,timeout);
        }
        return elements;
    }

    //A retry of Find multiple elements after a delay
    private static List<WebElement> retryFindElements(WebDriver driver, By locator){
        List<WebElement> elements = new ArrayList<WebElement>();
        try{
            log("retryFindElements",driver,locator.toString());
            Thread.sleep(mediumRetryTimeDelay); //This is a retry. It has previously failed, so we wait.
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            elements = driver.findElements(locator);
        }catch(Exception e){
            Logger.Error("Failed retry to find elements "+locator.toString());
        }
        return elements;
    }

    //A retry of Find multiple elements after a delay with a specific timeout
    private static List<WebElement> retryFindElements(WebDriver driver, By locator, int timeout){
        List<WebElement> elements = new ArrayList<WebElement>();
        try{
            log("retryFindElements",driver,locator.toString());
            Thread.sleep(mediumRetryTimeDelay); //This is a retry. It has previously failed, so we wait.
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            elements = driver.findElements(locator);
        }catch(Exception e){
            Logger.Error("Failed retry to find elements "+locator.toString());
        }
        return elements;
    }

    //Find element waiting for the presence of the element
    public static WebElement findElement(WebDriver driver, By locator){
        WebElement element = null;
        try{
            log("findElement",driver,locator.toString());
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element = driver.findElement(locator);
        }catch(Exception e){
            Logger.Error("Failed to find element "+locator.toString()+": retrying");
            retryFindElement(driver, locator);
        }
        return element;
    }

    //A retry of find element waiting for the presence of the element after a delay
    private static WebElement retryFindElement(WebDriver driver, By locator){
        WebElement element = null;
        try{
            log("retryFindElement",driver,locator.toString());
            Thread.sleep(shortRetryTimeDelay); //This is a retry. It has previously failed, so we wait.
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element = driver.findElement(locator);
        }catch(Exception e){
            Logger.Error("Failed to find element "+locator.toString());
        }
        return element;
    }

    //Javascript click for very specific scenarios
    public static void javascriptClick(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click", element);
    }

    //Scroll element into the viewport
    public static void scrollToElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollToView()", element);
    }

    /*
        The implementation of this will depend on the website.
        In general all select elements withing a same web-page will be exactly the same. So this should work for an entire web-page.
     */
    public static void selectOption(WebDriver driver, WebElement element, String option) {
        WebElement inputCountry = findElement(driver, By.cssSelector("input.select2-search__field"));
        inputCountry.sendKeys(option);

        WebElement result = findElement(driver, By.cssSelector("span.select2-results ul li"));
        result.click();
    }

    public static void selectNthElement(WebElement element, int nthElement) {
        try{
            element.findElements(By.tagName("li")).get(nthElement).click();
        }catch(NullPointerException e){
            Logger.Error("There is no such position: "+nthElement+", "+e);
        }
    }

    /**
     * A BlockingOverlays is basically a loading spinner that prevents the web to be used. This element does not always
     * appear. When it does, it is for less than 2 seconds. Thus, it requires a special treatment.
     */
    public static void waitForBlockingOverlays(WebDriver driver){
        final By overlayBlockers = By.cssSelector("div.blockUI.blockOverlay");
        List<WebElement> elements = new ArrayList<WebElement>();
        try{
            log("findOverlays",driver,overlayBlockers.toString());
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(overlayBlockers));
            elements = driver.findElements(overlayBlockers);
        }catch(Exception e){
            Logger.Error("Failed to find overlays "+overlayBlockers);
        }
        if(elements.size() > 0) {
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
        }
    }

    public static String getWebElementLocator(WebElement element){
        String rawElementString = element.toString();
        return rawElementString.split("->")[1];
    }

    private static void log(String methodName, WebDriver driver, String locator){
        String driverId = driver.toString().split("\\(")[1].split("\\)")[0];
        Logger.Info(methodName+": driver_"+driverId+" "+locator);
    }
}
