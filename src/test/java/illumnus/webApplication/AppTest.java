package illumnus.webApplication;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

/**
 * Unit test for simple App.
 */
public class AppTest {
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add("testng.xml");
		testng.setTestSuites(suites);
		testng.run();
	}
}
