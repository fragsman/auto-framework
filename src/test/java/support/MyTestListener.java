package support;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

import selenium.BaseTest;
import utils.Logger;

public class MyTestListener extends TestListenerAdapter {

    static private ExtentReports report;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    @Override  
    public void onStart(ITestContext context) {  
    	Logger.Info("onStart: Configuring extent report");
    	report = new ExtentReports();
    	ExtentSparkReporter spark = new ExtentSparkReporter("target/TestReport.html");
        report.attachReporter(spark); 
    }
    
    @Override
    public void onTestStart(ITestResult result) {
    	test.set(report.createTest(result.getName().split(" ")[0]));
    	test.get().log(Status.INFO, "Test started");
    }
    
    /* Here we will capture all messages from assertions and put them in the report.
	 * For a Standard Assertion if test passes it will end up here and a single message will be captured.
	 * For SoftAssertions to make the test pass, all assertions should have passed and all of them will be captured.
	 */
    @Override
    public void onTestSuccess(ITestResult result) {
    	if(result.isSuccess()) {
    		ITestContext context = result.getTestContext();
    		String testName = result.getName();
    		MyAssertMessage myAssertMessage = (MyAssertMessage)context.getAttribute(testName+"_msgs");
    		ArrayList<String> messages = myAssertMessage.getMessages();
    		for(String msg : messages){
    			test.get().log(Status.PASS, msg);
    		}
    	}
    	test.get().log(Status.INFO, "Test finished");
    	report.flush();
	}
    
    /* Here we will capture all messages from assertions and put them in the report.
	 * For a Standard Assertion if test fails it will end up here and a single message will be captured along with the screenshot.
	 * For SoftAssertions if at least one of the assertions fails the whole test will fail, however we still want to print the successful
	 * assertion messages. We aded a try/catch block because if there is any selenium error the test will also fail but assertion
	 * code won't be executed and messages won't be captured, thus it will fail when we try to access the assertion messages here.
	 */
    @Override
    public void onTestFailure(ITestResult result) {
    	if(!result.isSuccess()) {
    		ITestContext context = result.getTestContext();
    		String testName = result.getName();
    		
    		try {
    			MyAssertMessage myAssertMessage = (MyAssertMessage)context.getAttribute(testName+"_msgs");
        		ArrayList<String> messages = myAssertMessage.getMessages();
    	    	
        		if(myAssertMessage.getAssertType() == AssertType.SOFT)
        			for(String msg : messages){
        				test.get().log(Status.PASS, msg);
        			}
    		}catch(Exception e) {
    			Logger.Error("Selenium error. MyAssert code not reached, therefore messages are null");
    		}
	    	
    		test.get().log(Status.FAIL, result.getThrowable());
    		
    		Object currentTestInstance = result.getInstance();
    		File src= ((TakesScreenshot)((BaseTest)currentTestInstance).getDriver()).getScreenshotAs(OutputType.FILE);
    		String currentWorkingDir = System.getProperty("user.dir");
        	String filePath = currentWorkingDir+"\\target\\error_"+result.getName().split(" ")[0]+".png";
      
        	try {
        		FileUtils.copyFile(src, new File(filePath));
        	}
        	catch (IOException e){
        		Logger.Error("onTestFailure: error taking screenshot: "+e.getMessage());
        	}
        	test.get().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
    	}
    	test.get().log(Status.INFO, "Test finished");
    	report.flush();
    }
}
