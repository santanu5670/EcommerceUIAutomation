package Santanu.PageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santanu.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutFile;
	
	public Boolean productDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equals(productName));
		return match;
	}
	
	public CheckOutPage goTocheckOut() throws InterruptedException {
		scrollToViewWebElement(checkoutFile);
		checkoutFile.click();
		return new CheckOutPage(driver);
	}
				
}
