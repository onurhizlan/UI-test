package suites;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.Base;


public class TrendyolSuite extends Base {

	
	@Test
	@Parameters({ "environment", "email", "password"})
	public void login(String environment, String email, String password) throws InterruptedException{		
		
			lp.login(environment, email, password);
			Thread.sleep(10000);
	}	
	
	@Test
	@Parameters({ "environment", "email", "password", "searchText", "lowPrice", "highPrice"})
	public void addMonsterProduct(String environment, String email, String password, String searchText, String lowPrice, String highPrice) throws Exception{		
		
			lp.login(environment, email, password);
			mp.search(searchText);
			mp.selectMonsterCategory();
			mp.selectPriceCategory(lowPrice, highPrice);
			mp.addMonsterProduct();
	}
	
	@Test
	@Parameters({ "environment", "email", "password", "searchText"})
	public void addShirtProduct(String environment, String email, String password, String searchText) throws Exception{		
		
			lp.login(environment, email, password);
			mp.search(searchText);
			mp.selectFavouriteProduct();
	}
	
	@Test
	@Parameters({ "environment", "email", "password"})
	public void checkComponent(String environment, String email, String password) throws Exception{		
		
			mp.componentforKadýn();

	}
}
