package com.onlineStore.mercari.testCase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.onlineStore.mercari.addVerifyNewAddress.AddVerifyNewAddressPage;
import com.onlineStore.mercari.testBase.ExtentTestManager;
import com.onlineStore.mercari.testBase.TestBase;

public class TC002_VerifyNewAddressRegistered extends TestBase {

	public static final Logger log = Logger.getLogger(TC002_VerifyNewAddressRegistered.class.getName());

	public static ExtentTest child;

	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		init();
	}

	public TC002_VerifyNewAddressRegistered() {

	}

	@Test(priority = 1, enabled = false)
	public void verifyNewAddressRegistered() throws Exception {

		child = ExtentTestManager.startTest("Verify new address registered", "Verify new address registered");
		child.log(Status.INFO, "Verify new address registered");

		AddVerifyNewAddressPage addVerifyNewAddressPage = new AddVerifyNewAddressPage(driver);
		addVerifyNewAddressPage.verifyNewAddressRegistered();

	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}
