package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class MainPage {

	private WebDriver driver;
	private BaseFunctions bf;

	public MainPage(WebDriver driver) {
		this.driver = driver;
		bf = new BaseFunctions(driver);
	}

	@FindBy(xpath = "//label[contains(text(), 'Þifre')]//following::input[1]")
	private WebElement passwordInput; 

	@FindBy(xpath = "//div[contains(@title,'Kapat')]")
	private WebElement mainPagePopUp;
	
	@FindBy(xpath = "//input[@placeholder='Aradýðýnýz ürün, kategori veya markayý yazýnýz']")
	private WebElement searchInput;
	
	@FindBy(xpath = "//input[@placeholder='Aradýðýnýz ürün, kategori veya markayý yazýnýz']//following::i[1]")
	private WebElement searchClick;
	
	@FindBy(xpath = "//div[contains(text(), 'Marka')]//following::div[contains(text(), 'Monster')]")
	private WebElement selectMonster;
	
	@FindBy(xpath = "//input[@placeholder='En Az']")
	private WebElement selectLowPriceInput;
	
	@FindBy(xpath = "//input[@placeholder='En Çok']")
	private WebElement selectHighPriceInput;
	
	@FindBy(xpath = "//input[@placeholder='En Çok']//following::button[1]")
	private WebElement priceSearch;
	
	@FindBy(xpath = "(//span[contains(text(), 'MONSTER')])[1]")
	private WebElement selectFirstMonsterProduct;
	
	@FindBy(xpath = "//div[text()='Sepete Ekle']")
	private WebElement addToCart;	
	
	@FindBy(xpath = "//a[text()='Sipariþi Tamamla']")
	private WebElement checkProduct;
	
	@FindBy(xpath = "(//div[@class='image-container'])[1]//following::i[2]")
	private WebElement addFavouriteShirt;
	
	@FindBy(xpath = "//p[contains(text(), 'Favorilerim')]")
	private WebElement favouriteButton; 
	
	@FindBy(xpath = "//span[text()='Sepete Ekle']")
	private WebElement addToCartforFavouritePage;	
	
	@FindBy(xpath = "//span[text()='S']")
	private WebElement shirtSize;	
	
	@FindBy(xpath = "//span[text()='Sepete Eklendi']")
	private WebElement checAddToCartforFavouritePage;	
	
	@FindBy(xpath = "(//a[text()='Kadýn'])[1]")
	private WebElement womenTag;
	
	@FindBy(xpath = "(//span[@class='image-container'])[1]")
	private WebElement firstComponentforWomen;
	
	@FindBy(xpath = "(//div[@class='image-container']//following::span)[2]")
	private WebElement firstProductForWomen;
	
	@FindBy(xpath = "//div[@class='product-brand-description two-line-text']//span[@class = 'name']")
	private WebElement products;
	
	
	public void search(String searchText) throws Exception{		
		
			bf.write(searchInput, searchText);
			bf.click(searchClick);
	}
	
	public void selectMonsterCategory() throws Exception{		
		
		bf.click(selectMonster);
}

	public void selectPriceCategory(String lowPrice, String highPrice) throws Exception{		
		
		bf.write(selectLowPriceInput, lowPrice);
		bf.write(selectHighPriceInput, highPrice);
		bf.click(priceSearch);
}
	
	public void addMonsterProduct() throws Exception{		
		
			bf.click(selectFirstMonsterProduct);
			bf.changeToLastTab();
			bf.click(addToCart);
			bf.checkElement(checkProduct);
}
	
	public void selectFavouriteProduct() throws Exception{		
		bf.clickWithActions(favouriteButton);
		bf.click(addFavouriteShirt);
		bf.click(favouriteButton);
		bf.click(addToCartforFavouritePage);
		bf.click(shirtSize);
		bf.checkElement(checAddToCartforFavouritePage);
}
	
	public void checkFirst4Product() {
		List<WebElement> firstElements = driver.findElements(By.xpath("//div[@class='product-brand-description two-line-text']//span[@class = 'name']"));
		firstElements.stream().limit(4).forEach((element3)->{
		boolean fpifk= element3.isDisplayed();
		Reporter.log("Product displayed is : " + fpifk);
		System.out.println("Product displayed is :"+fpifk);});
	}
	
	public void checkFirst4Image() {	
		List<WebElement> secondElements = driver.findElements(By.xpath("//div[@class = 'image-container']//img"));
		secondElements.stream().limit(4).forEach((element2)->{	
		boolean fpifk2= element2.isDisplayed();
		Reporter.log("Image displayed is : " + fpifk2);
		System.out.println("Image displayed is :"+fpifk2);});
	}
	
	
	
	public void componentforKadýn() throws Exception{		
		
		List<WebElement> firstElements2 = driver.findElements(By.xpath("//ul[@class = 'main-nav']//li[@class='tab-link']"));
		
		for (int i = 1; i <= firstElements2.size(); i++) {
			try {
				String xpath = "//ul[@class = 'main-nav']//li[@class='tab-link'][%s]";
				WebElement element = driver.findElement(By.xpath(String.format(xpath, i)));
				element.click();
				Thread.sleep(3000);
				
				bf.click(firstComponentforWomen);
				checkFirst4Product();
				checkFirst4Image();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}	
	}
}
