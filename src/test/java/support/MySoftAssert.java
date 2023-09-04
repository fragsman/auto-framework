package support;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

/*
 * An assertion will normally throw a message only when it fails.
 * MySoftAssert was created to wrap original SoftAssert (SA) with the purpose to print the assertion message even when it is passing.
 * 
 * To do this we will have to create a method in this class for every assertion that want to be used in the framework. 
 * For example assertEquals(String, String, String) is one of them. If we want to compare any other data type we should create 
 * that method which will, at the end, call the original Assert.assertEquals() but in our method we will save the message within 
 * the test context.
 * At the end of the SAs it will call assertAll() here two scenarios come into play:
 *  - If all asserts within the SA passes the test will PASS and one message will be captured per each SA
 *  - If one or more assert fails the test will be detected as FAIL, however we will save the passed assertions messages
 *   adding logic the overriden onAssertSuccess method.
 * 
 * As the massage will end up in an HTML report we can add any formatting we want inside the string.
 */

public class MySoftAssert extends SoftAssert {

	private ITestContext context;
	private ArrayList<String> messages;
	private String methodName;
	private MyAssertMessage myAssertMessage;
	
	public MySoftAssert(ITestContext context, String methodName) {
		this.context = context;
		messages = new ArrayList<String>();
		this.methodName = methodName;
		this.myAssertMessage = new MyAssertMessage(AssertType.SOFT);
	}
	
	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
		String message = assertCommand.getMessage();
		String expected = assertCommand.getExpected().toString();
		String actual = assertCommand.getActual().toString();
		messages.add("<i>"+message+" expected ["+expected+"] and found ["+actual+"]</i>");
	}
	
	@Override
	public void assertAll() {
		myAssertMessage.setMessages(messages);
		context.setAttribute(methodName+"_msgs", myAssertMessage);
		super.assertAll();
	}
}
