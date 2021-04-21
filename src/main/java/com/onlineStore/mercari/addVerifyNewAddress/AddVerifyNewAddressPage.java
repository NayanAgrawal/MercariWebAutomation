package com.onlineStore.mercari.addVerifyNewAddress;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.onlineStore.mercari.testBase.ExtentTestManager;
import com.onlineStore.mercari.testBase.ReadPropertiesFile;
import com.onlineStore.mercari.testCase.TC002_VerifyNewAddressRegistered;

/**
 * Add new address and verify it.
 *
 * @author Nayan Agrawal
 * @version 1.0
 * @since 2021-04-21
 */
public class AddVerifyNewAddressPage extends TC002_VerifyNewAddressRegistered {

	public WebDriver driver;

	@FindBy(xpath = "//a[@class='sc-gqPbQI bKchSu'][@href='/jp/category/']")
	WebElement verifyHomepage;

	@FindBy(xpath = "//a[@class='sc-eLExRp gpkxTc']")
	WebElement loginButton;

	@FindBy(xpath = "//div[@class='login-no-account']/p")
	WebElement loginPageVerify;

	@FindBy(xpath = "//input[@class='login-input-text input-default'][@name='email']")
	WebElement userNameText;

	@FindBy(xpath = "//input[@class='login-input-text input-default'][@name='password']")
	WebElement passwordText;

	@FindBy(xpath = "//button[@class='login-submit btn-default btn-red']")
	WebElement submitButton;

	@FindBy(xpath = "//div[@class='user-name']")
	WebElement verifyUserSignedIn;

	@FindBy(id = "myPage-1")
	WebElement myPageButton;

	@FindBy(id = "user-profile")
	WebElement verifyUserProfilePage;

	@FindBy(id = "personal Info link")
	WebElement personalInfoButton;

	@FindBy(id = "user-PersonalInfo")
	WebElement verifyPersonalInfoPage;

	@FindBy(xpath = "//a[@class='sc-ship add']")
	WebElement shippingAddressButton;

	@FindBy(xpath = "//a[@class='Address list']")
	WebElement verifyShippingAddresses;

	@FindBy(xpath = "//a[@class='new address']")
	WebElement addNewShippingAddressButton;

	@FindBy(xpath = "//a[@class='new address']")
	WebElement verifyNewShippingAddressPage;

	@FindBy(id = "user-name")
	WebElement nameTextBox;

	@FindBy(id = "new Address1")
	WebElement AddressLine1Text;

	@FindBy(id = "new Address2")
	WebElement AddressLine2Text;

	@FindBy(id = "city")
	WebElement CityText;

	@FindBy(id = "country")
	WebElement countryText;

	@FindBy(id = "city pincode")
	WebElement PinCodeText;

	@FindBy(id = "pNumber")
	WebElement phoneNumberText;

	@FindBy(xpath = "//a[@class='saveAddress']")
	WebElement saveButton;

	public AddVerifyNewAddressPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Verify new address registered
	 *
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void verifyNewAddressRegistered() throws InterruptedException, IOException {

		explicatWait(driver, verifyHomepage);

		System.out.println(driver.getTitle());

		assertEquals("カテゴリーから探す", verifyHomepage.getText());

		loginPage(ReadPropertiesFile.getProperty("Username"), ReadPropertiesFile.getProperty("Password"));

		shippingAddressesPage();

		newAddressFormData();
	}

	/**
	 * Login to Mercari application
	 *
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void loginPage(String userNameValue, String PasswordValue) throws IOException, InterruptedException {

		loginButton.click();

		Assert.assertEquals("アカウントをお持ちでない方はこちら", loginPageVerify.getText());

		userNameText.sendKeys(userNameValue);
		passwordText.sendKeys(PasswordValue);

		// Assuming captcha is disabled by developer in test environment
		submitButton.click();

		child.log(Status.INFO, "Verified login page data " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "verifiedLoginPage")).toString());

		// Verify user signedIn successfully
		Assert.assertEquals("userName", verifyUserSignedIn.getText());

	}

	/**
	 * Open shipping addresses page from user profile
	 *
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void shippingAddressesPage() throws IOException, InterruptedException {

		// Click on My page from homescreen
		myPageButton.click();
		explicatWait(driver, verifyUserProfilePage);
		Assert.assertEquals("user Profile", verifyUserProfilePage.getText());

		// Click on personal information button in user profile page
		personalInfoButton.click();
		explicatWait(driver, verifyPersonalInfoPage);
		Assert.assertEquals("PersonalInfo", verifyPersonalInfoPage.getText());

		// Click on shipping addresses button
		shippingAddressButton.click();
		explicatWait(driver, verifyShippingAddresses);
		Assert.assertEquals("user shipping addresses", verifyShippingAddresses.getText());

	}

	/**
	 * New shipping address form data
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void newAddressFormData() throws IOException, InterruptedException {

		// Click Add button to add new shipping addresses
		addNewShippingAddressButton.click();
		explicatWait(driver, verifyNewShippingAddressPage);
		Assert.assertEquals("New Shiiping Address form", verifyNewShippingAddressPage.getText());

		// Reading Form data from config.properties file
		nameTextBox.sendKeys(ReadPropertiesFile.getProperty("Name"));
		AddressLine1Text.sendKeys(ReadPropertiesFile.getProperty("AddressLine1"));
		AddressLine2Text.sendKeys(ReadPropertiesFile.getProperty("AddressLine2"));
		CityText.sendKeys(ReadPropertiesFile.getProperty("City"));
		countryText.sendKeys(ReadPropertiesFile.getProperty("Country"));
		PinCodeText.sendKeys(ReadPropertiesFile.getProperty("PinCode"));
		phoneNumberText.sendKeys(ReadPropertiesFile.getProperty("PhoneNumber"));

		child.log(Status.INFO, "Verify new shipping address data " + ExtentTestManager.getTest()
				.addScreenCaptureFromPath(getScreenshot(driver, "NewShippingAddress")).toString());

		// click on save button
		saveButton.click();

		// Assume address added successfully message is displayed
		// Bottom header is still appearing & user navigated from MyPage to shipping
		// addresses again
		shippingAddressesPage();

		String verifyNewlyAddedShippingAddress = "//a[@class='new address'][@text='"
				+ ReadPropertiesFile.getProperty("Name") + "']]";

		// Verify newly added shipping address
		Assert.assertEquals(ReadPropertiesFile.getProperty("Name"),
				driver.findElement(By.xpath(verifyNewlyAddedShippingAddress)).getText());

	}

}
