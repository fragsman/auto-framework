package org.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.DriverManager;
import utils.Logger;

public class MyTestListener extends TestListenerAdapter {

    static private ExtentReports report;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected DriverManager driverManager;
    
    @Override  
    public void onStart(ITestContext context) {  
    	Logger.Info("BeforeSuite: Configuring extent report");
    	report = new ExtentReports();
    	ExtentSparkReporter spark = new ExtentSparkReporter("target/TestReport.html");
        report.attachReporter(spark); 
        driverManager =  new DriverManager();
    }
    
    @Override
    public void onTestStart(ITestResult result) {
    	test.set(report.createTest(result.getName().split(" ")[0]));
    	test.get().log(Status.INFO, "Test started");
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
    	if(result.isSuccess())
    		test.get().log(Status.PASS, "pasa");
    	test.get().log(Status.INFO, "Test finished");
    	report.flush();
	}
    
    @Override
    public void onTestFailure(ITestResult result) {
    	if(!result.isSuccess()) {
    		test.get().log(Status.FAIL, result.getThrowable());
    		
    		Object currentTestInstance = result.getInstance();
    		File src= ((TakesScreenshot)((BaseTest)currentTestInstance).getDriver()).getScreenshotAs(OutputType.FILE);
    		String currentWorkingDir = System.getProperty("user.dir");
        	String filePath = currentWorkingDir+"\\target\\error_"+result.getName().split(" ")[0]+".png";
      
        	try {
        		FileUtils.copyFile(src, new File(filePath));
        	}
        	catch (IOException e){
        		System.out.println(e.getMessage());
        	}
        	test.get().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
    	}
    	test.get().log(Status.INFO, "Test finished");
    	report.flush();
    }
}
