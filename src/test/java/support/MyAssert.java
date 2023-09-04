package support;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.ITestContext;

/*
 * An assertion will normally throw a message only when it fails.
 * MyAssert was created to wrap original Assert with the purpose to print the assertion message even when it is passing.
 * 
 * To do this we will have to create a method in this class for every assertion that want to be used in the framework. 
 * For example assertEquals(String, String, String) is one of them. If we want to compare any other data type we should create 
 * that method which will, at the end, call the original Assert.assertEquals() but in our method we will save the message 
 * within the test context. Then, whenever the test passes it will print the message.
 * 
 * As the massage will end up in an HTML report we can add any formatting we want inside the string.
 * Note: We use a List of Messages so we can use the same for SoftAssertions in which we need to have 1 message per assertion.
 */

public class MyAssert extends Assert {
	
	private String methodName;
	private ITestContext context;
	private MyAssertMessage myAssertMessage;
	
	public MyAssert(ITestContext context, String methodName) {
		this.methodName = methodName;
		this.context = context;
		this.myAssertMessage = new MyAssertMessage(AssertType.STANDARD);
	}

	public void assertEq(String actual, String expected, String message) {
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("<i>"+message+" expected ["+expected+"] and found ["+actual+"]</i>");
		myAssertMessage.setMessages(messages);
		context.setAttribute(methodName+"_msgs", myAssertMessage);
		
		Assert.assertEquals(actual, expected, message);
	}
	
	public void assertEq(boolean actual, boolean expected, String message) {
		ArrayList<String> messages = new ArrayList<String>();
		messages.add("<i>"+message+" expected ["+expected+"] and found ["+actual+"]</i>");
		myAssertMessage.setMessages(messages);
		context.setAttribute(methodName+"_msgs", myAssertMessage);
		
		Assert.assertEquals(actual, expected, message);
	}
}
