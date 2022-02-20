package base;

import java.util.Collections;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pages.LoginPage;
import pages.MainPage;


public class Base {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public String myBrowser, environment;
	protected LoginPage lp;
	protected MainPage mp;
	
	
	public void initPages() {
		lp = PageFactory.initElements(driver, LoginPage.class);
		mp = PageFactory.initElements(driver, MainPage.class);
	}
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	@Parameters({ "myBrowser", "environment" })
	public void initDriver(String myBrowser, String environment) {

		this.environment = environment;
		if (System.getProperty("os.name").contains("Window")) {
			if (myBrowser.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

				DesiredCapabilities capability = new DesiredCapabilities();
				capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

				ChromeOptions options = new ChromeOptions();

				options.addArguments("--start-maximized");
				options.addArguments(new String[] { "disable-infobars" });
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.addArguments("--allow-running-insecure-content");
				options.addArguments("ignore-certificate-errors");
			//	options.addArguments("headless");
				options.addArguments("window-size=1920,1080");
				capability.setCapability(ChromeOptions.CAPABILITY, options);

				driver = new ChromeDriver(capability);
				wait = new WebDriverWait(driver, 90);
				driver.manage().window().maximize();

			} else if (myBrowser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				
				DesiredCapabilities capability = new DesiredCapabilities();
				capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				driver = new FirefoxDriver(capability);
				wait = new WebDriverWait(driver, 90);
				driver.manage().window().maximize();
				 
			}
		}
		initPages();
	}
	
	@AfterMethod
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterTest
	public void closeBrowserr() {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}
	
}
