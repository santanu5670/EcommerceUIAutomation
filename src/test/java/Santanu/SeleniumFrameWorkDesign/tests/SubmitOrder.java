package Santanu.SeleniumFrameWorkDesign.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Santanu.PageObject.CartPage;
import Santanu.PageObject.CheckOutPage;
import Santanu.PageObject.ConfirmationPage;
import Santanu.PageObject.OrderPage;
import Santanu.PageObject.ProductCatalogue;
import Santanu.TestComponent.BaseTest;


public class SubmitOrder extends BaseTest {

	String productName = "ZARA COAT 3";
	String countryName = "India";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		
		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
	
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage cartpage = productCatalogue.goToCartPage();
		
		Boolean match = cartpage.productDisplay(input.get("productName"));
		Assert.assertTrue(match);
		
		CheckOutPage checkoutpage = cartpage.goTocheckOut();
		checkoutpage.checkOutDetails(input.get("countryName"));
		
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmessage = confirmationpage.getConfirmationMessage();
		Assert.assertEquals(confirmessage, "THANKYOU FOR THE ORDER.");

	}
	
	@Test(dataProvider="getData",dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderpage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(input.get("productName")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Santanu//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
//		return new Object[][] {{data.get(0)}};
	}
	
	/*@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "Rajib123@gmail.com");
		map.put("password", "Rajib@12");
		map.put("productName", "ZARA COAT 3");
		map.put("countryName", "India");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "rohit123@gmail.com");
		map1.put("password", "Rohit@12");
		map1.put("productName", "ADIDAS ORIGINAL");
		map1.put("countryName", "India");
		
		return new Object[][] {{map},{map1}};
	}*/
	
	/*@DataProvider
	public Object[][] getData() {	
		return new Object[][] {{"Rajib123@gmail.com","Rajib@12","ZARA COAT 3","India"},{"rohit123@gmail.com","Rohit@12","ADIDAS ORIGINAL","India"}};
	}*/
	
	//ProductCatalogue productCatalogue = landingpage.loginApplication("Rajib123@gmail.com", "Rajib@12");
	//OrderPage orderpage = productCatalogue.goToOrderPage();
	
	//	OrderPage orderPage2 = new OrderPage(driver);
	//	orderPage2.goToOrderPage();

}
