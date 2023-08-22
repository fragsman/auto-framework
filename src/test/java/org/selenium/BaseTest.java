package org.selenium;

import POM.BasePage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.DriverManager;
import utils.Logger;

public class BaseTest extends TestListenerAdapter{

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected DriverManager driverManager;
    static private ExtentReports report;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    @BeforeSuite
    //Will be run before all tests in this suite have run.
	public void setUpExtentReport() {
		Logger.Info("BeforeSuite: Configuring extent report");
    	report = new ExtentReports();
    	ExtentSparkReporter spark = new ExtentSparkReporter("target/TestReport.html");
        report.attachReporter(spark);
	}
    
    /*@BeforeTest
    //Will be run before any test method belonging to the classes inside the <test> tag is run.
    public void beforeTestDoSomething(){
    	//Nothing implemented here yet
    }*/
    
    @BeforeMethod
    public void navigateToWebsiteToTest(){
        Logger.Info("BeforeMethod: Creating driver, opening browser and navigating to homepage");
        driverManager = new DriverManager();
        driver.set(driverManager.getDriver());
        BasePage basePage = new BasePage(getDriver());
        basePage.navigateToMainPage();
    }

    @AfterMethod
    public void closeBrowser(){
    	Logger.Info("AfterMethod: Closing driver");
    	driver.get().close();
        driver.get().quit();
    }

    /*@AfterTest
    //Will be run after all the test methods belonging to the classes inside the <test> tag have run.
    public void doSomethingAfterTest(){
    	//Nothing implemented here yet
    }*/
    
    @AfterSuite
    //Will be run after all tests in this suite have run.
    public void flushReports() {
    	Logger.Info("AfterSuite: Flushing test report");
        report.flush();
    }
    
    public WebDriver getDriver() {
        return driver.get();
    }
    
    //Test NG Listeners
    
    @Override
    public void onTestStart(ITestResult result) {
    	test.set(report.createTest(result.getName().split(" ")[0]));
    	test.get().log(Status.INFO, "Test started");
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
    	test.get().log(result.isSuccess()? Status.PASS: Status.FAIL, result.getThrowable());
    	test.get().log(Status.INFO, "Test finished");
	}
    
    @Override
    public void onTestFailure(ITestResult result) {
    	test.get().log(result.isSuccess()? Status.PASS: Status.FAIL, result.getThrowable());
    	/*File src= ((TakesScreenshot)driverManager.getDriver()).getScreenshotAs(OutputType.FILE);
    	String filename = "target/error_"+result.getTestName()+".png";
    	try {
    		FileUtils.copyFile(src, new File(filename));
    	}
    	catch (IOException e){
    		System.out.println(e.getMessage());
    	}
    	test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(filename).build());*/
    	test.get().log(Status.INFO, "Test finished");
    }
}
