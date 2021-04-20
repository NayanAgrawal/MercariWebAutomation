package com.onlineStore.mercari.testCase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.onlineStore.mercari.homePage.Homepage;
import com.onlineStore.mercari.testBase.ExtentTestManager;
import com.onlineStore.mercari.testBase.TestBase;

public class TC001_VerifyHomePage extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_VerifyHomePage.class.getName());
	public static ExtentTest child;

	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		init();
	}

	public TC001_VerifyHomePage() {

	}

	@Test(priority = 1, enabled = true)
	public void verifyHomepage() throws Exception {

		child = ExtentTestManager.startTest("Verify homepage", "Verify homepage");
		child.log(Status.INFO, "Verify Mercari homepage");

		Homepage homepage = new Homepage(driver);
		homepage.verifyHomepageTab();

	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}
	}
}
