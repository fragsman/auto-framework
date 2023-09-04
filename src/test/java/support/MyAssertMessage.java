package support;

import java.util.ArrayList;

public class MyAssertMessage {
	
	private ArrayList<String> messages;
	private AssertType assertType;

	public MyAssertMessage(AssertType assertType) {
		this.assertType = assertType;
	}
	
	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	
	public AssertType getAssertType() {
		return assertType;
	}
}
