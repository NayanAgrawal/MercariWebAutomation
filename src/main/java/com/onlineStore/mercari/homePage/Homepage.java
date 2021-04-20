package com.onlineStore.mercari.homePage;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.onlineStore.mercari.testCase.TC001_VerifyHomePage;
import com.onlineStore.mercari.testBase.ExtentTestManager;

/**
 * Verify homepage loaded correctly.
 *
 * @author Nayan Agrawal
 * @version 1.0
 * @since 2021-04-21
 */
public class Homepage extends TC001_VerifyHomePage {

	public WebDriver driver;

	@FindBy(xpath = "//a[@class='sc-gqPbQI bKchSu'][@href='/jp/category/']")
	WebElement verifyHomepage;

	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Verify homepage is loaded
	 * 
	 * @throws IOException
	 */
	public void verifyHomepageTab() throws IOException {

		explicatWait(driver, verifyHomepage);

		System.out.println(driver.getTitle());

		assertEquals("カテゴリーから探す", verifyHomepage.getText());

		child.log(Status.INFO, "Homepage screenshot - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "MercariHomepage")).toString());
		
		child.log(Status.INFO, "Verified mercari homepage");
		
	}

}
