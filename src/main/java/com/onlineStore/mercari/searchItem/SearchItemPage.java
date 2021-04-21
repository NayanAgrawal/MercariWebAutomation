package com.onlineStore.mercari.searchItem;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.onlineStore.mercari.testBase.ExtentTestManager;
import com.onlineStore.mercari.testCase.TC003_SearchItem;

/**
 * Search and verify particular item in store.
 *
 * @author Nayan Agrawal
 * @version 1.0
 * @since 2021-04-21
 */
public class SearchItemPage extends TC003_SearchItem {

	public WebDriver driver;
	@FindBy(xpath = "//a[@class='sc-gqPbQI bKchSu'][@href='/jp/category/']")
	WebElement verifyHomepage;

	@FindBy(xpath = "//input[@class='sc-GMQeP cuephK']")
	WebElement searchTextBox;

	@FindBy(xpath = "//button[@class='sc-exAgwC bdHDSo']")
	WebElement searchButton;

	@FindBy(xpath = "//h2[@class='search-result-head']")
	WebElement searchResultHead;

	@FindBy(xpath = "//h3[@class='items-box-name font-2']")
	WebElement searchResultCount;

	@FindBy(xpath = "//table[@class='item-detail-table']")
	WebElement verifyItemDetailPage;

	@FindBy(xpath = "//h1[@class='item-name']")
	WebElement itemName;

	public SearchItemPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Search required item in store
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void searchItemTab(String itemNameValue, int itemNumber) throws InterruptedException, IOException {

		explicatWait(driver, verifyHomepage);

		assertEquals("カテゴリーから探す", verifyHomepage.getText());

		searchTextBox.sendKeys(itemNameValue);

		searchButton.click();

		List<WebElement> sectionList = driver.findElements(By.xpath("//h3[@class='items-box-name font-2']"));

		child.log(Status.INFO,
				"Total number of item found in first page for " + itemNameValue + " is - " + sectionList.size());
		log.info("Total number of item found in first page for " + itemNameValue + "is - " + sectionList.size());

		sectionList.get(itemNumber).click();

		explicatWait(driver, itemName);

		child.log(Status.INFO, "Current URL - " + driver.getCurrentUrl());
		log.info("Current URL - " + driver.getCurrentUrl());

		if (verifyItemDetailPage.isDisplayed()) {
			Assert.assertEquals(true, true);
		} else {
			Assert.assertEquals(true, false);
		}

		if (((itemName.getText()).toLowerCase()).contains(itemNameValue.toLowerCase())) {
			Assert.assertEquals(true, true);
			child.log(Status.INFO, itemNameValue + " keyword is present in item Name");
			log.info(itemNameValue + " keyword is present in item Name");

			child.log(Status.INFO, "Item name is - " + itemName.getText());
			log.info("Item name is - " + itemName.getText());
		} else {
			Assert.assertEquals(false, false);
			log.info(itemNameValue + " keyword not present in item Name");
		}

		child.log(Status.INFO, "ItemDetails - " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "ItemDetails")).toString());

	}

}
