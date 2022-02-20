package pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class BaseFunctions extends Base{

	private WebDriver driver;
	private WebDriverWait wait;
	Actions act;

	public BaseFunctions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 180);
	}

	public void click(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void write(WebElement element, String text) throws InterruptedException {
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(text);
	}
	
	public void changeToLastTab() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size() - 1));
	}
	
	public void checkElement(WebElement element) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickWithActions(WebElement element) throws InterruptedException {

		Thread.sleep(10000);
		new Actions(driver).moveByOffset(50, 120).click().build().perform(); 

	}
	
	public String getTextOnElement(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		String text = "";
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		text = element.getText();
		return text;
	}
	
	
	
}
