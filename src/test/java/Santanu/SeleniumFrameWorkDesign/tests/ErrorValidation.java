package Santanu.SeleniumFrameWorkDesign.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Santanu.PageObject.CartPage;
import Santanu.PageObject.ProductCatalogue;
import Santanu.TestComponent.BaseTest;

import Santanu.TestComponent.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidations() throws InterruptedException, IOException {
	
		landingpage.loginApplication("sanjay123@gmail.com", "Sanjay@12");
		Assert.assertEquals(landingpage.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test
	public void productPageErrorValidations() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
	
		ProductCatalogue productCatalogue = landingpage.loginApplication("rohit123@gmail.com", "Rohit@12");
		
		productCatalogue.addProductToCart(productName);
		
		CartPage cartpage = productCatalogue.goToCartPage();
		
		Boolean match = cartpage.productDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
