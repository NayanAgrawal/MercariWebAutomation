package com.onlineStore.mercari.testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

/**
 * Created reusable methods.
 *
 * @author Nayan Agrawal
 * @version 1.0
 * @since 2021-04-21
 */
public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	String browser = ReadPropertiesFile.getProperty("browser");

	public WebDriver driver;

	@FindBy(xpath = "//div[@class='rdtDays']//tbody")
	WebElement selectDateOfQuiz;

	/**
	 * Initialize a browser, enter URL
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void init() throws InterruptedException, IOException {
		selectBrowser(browser);
		getUrl(ReadPropertiesFile.getProperty("applicationUrl"));
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

	}

	/**
	 * Select a browser to perform action
	 * 
	 * @param Browser name
	 * @throws InterruptedException
	 */
	public void selectBrowser(String browser) throws InterruptedException {
		if (browser.equalsIgnoreCase("chrome")) {
			log.info("Creating object of " + browser);
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			TimeUnit.SECONDS.sleep(1);

		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("headless")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless", "--window-size=1200,800", "--ignore-certificate-errors", "--silent");
			driver = new ChromeDriver(options);

		}
	}

	/**
	 * Get application URL
	 * 
	 * @param URL
	 */
	public void getUrl(String url) {

		System.out.println(url);

		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}

	/**
	 * Handle multiple browser windows
	 * 
	 */
	public void getWindowHandler() {
		String parentGUID = driver.getWindowHandle();
		Set<String> allGUID = driver.getWindowHandles();

		try {

			for (String guid : allGUID) {
				// one enter into if blobk if the GUID is not equal to parent window's GUID
				if (!guid.equals(parentGUID)) {
					// switch to the guid
					driver.switchTo().window(guid);
					// break the loop
					break;
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Generate screenshot
	 * 
	 * @param WebDriver and image name
	 */
	public String getScreenshot(WebDriver driver, String imageName) throws IOException {

		if (imageName.equals("")) {
			imageName = "_blank";
		}

		Calendar calander = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
				+ "/src/main/java/com/onlineStore/mercari/screenshot/";

		String actualImageName = reportDirectory + imageName + "_" + format.format(calander.getTime()) + ".png";
		File destFile = new File(actualImageName);
		FileUtils.copyFile(image, destFile);

		Reporter.log("<a href= '" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
				+ "' height='100' width ='100'/></a>");

		return actualImageName;

	}

	/**
	 * Wait till element is clickable
	 * 
	 * @param WebDriver and xpathValue
	 */
	public void explicatWait(WebDriver driver, WebElement verifyHomepage) {
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(verifyHomepage));
	}

}
