package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;



public class LoginPage {

	private WebDriver driver;
	private BaseFunctions bf;
	private WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 180);
		bf = new BaseFunctions(driver);
	}

	
	@FindBy(xpath = "//p[contains(text(), 'Giriþ Yap')]")
	private WebElement selectLogin;
	
	@FindBy(xpath = "//label[contains(text(), 'E-Posta')]//following::input[1]")
	private WebElement emailnput;
	
	@FindBy(xpath = "//label[contains(text(), 'Þifre')]//following::input[1]")
	private WebElement passwordInput; 

	@FindBy(xpath = "//div[contains(@title,'Kapat')]")
	private WebElement mainPagePopUp;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;
	
	
	public void login(String environment, String email, String password) throws InterruptedException {		
		
		driver.navigate().to(environment);
		try {
			Thread.sleep(2000);
			bf.click(mainPagePopUp);
			bf.click(selectLogin);
			bf.write(emailnput, email);
			bf.write(passwordInput, password);
			bf.click(loginButton);
	 		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Hesabým']")));
			Reporter.log("Login successful");
		} catch (Exception e) {
			bf.click(selectLogin);
			bf.write(emailnput, email);
			bf.write(passwordInput, password);
			bf.click(loginButton);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Hesabým']")));
			Reporter.log("Login successful");
		}
		
	}
	
}
