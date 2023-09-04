package selenium.askomcdh;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.BaseTest;
import support.MyAssert;

public class NumericTests extends BaseTest{

	//This method will provide data to any test method that declares that its Data Provider is named "data_provider"
	@DataProvider(name = "data_provider")
	public Object[][] createData() {
	 return new Object[][] {
	   { "Cedric", 36 },
	   { "Anne", 37},
	 };
	}
	 
	//This test method declares that its data should be supplied by the Data Provider named "data_provider"
	//If the data set contains N element the test will run N times, one for each set of data
	@Test(dataProvider = "data_provider", groups = {"non_browser_tests"})
	public void numberTests(String name, int age) {
		//Let's suppose that if the age is a pair number the user can be logged in
		boolean logged = (age % 2 == 0);
		
		MyAssert ma = new MyAssert(getITestContext(), getCurrentMethodName());
		ma.assertEq(logged, true, "The user wasn't accepted into the system because it's age was a pair number: "+age);
	}
}
