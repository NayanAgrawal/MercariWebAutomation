package com.onlineStore.mercari.testCase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.onlineStore.mercari.searchItem.SearchItemPage;
import com.onlineStore.mercari.testBase.ExtentTestManager;
import com.onlineStore.mercari.testBase.ReadPropertiesFile;
import com.onlineStore.mercari.testBase.TestBase;

public class TC003_SearchItem extends TestBase {

	public static final Logger log = Logger.getLogger(TC003_SearchItem.class.getName());

	public static ExtentTest child;

	@BeforeMethod
	public void setup() throws InterruptedException, IOException {
		init();
	}

	public TC003_SearchItem() {

	}

	@Test(priority = 1, enabled = true)
	public void searchItem() throws Exception {

		child = ExtentTestManager.startTest("Search item", "Search item");
		child.log(Status.INFO, "Search required item from store");

		SearchItemPage searchItemPage = new SearchItemPage(driver);
		searchItemPage.searchItemTab(ReadPropertiesFile.getProperty("ItemSearchName"), 3);

	}

	@AfterMethod
	public void endTest() {
		if (driver != null) {
			driver.quit();
		}
	}

}
