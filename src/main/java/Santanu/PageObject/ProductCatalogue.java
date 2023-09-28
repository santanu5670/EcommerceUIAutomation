package Santanu.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santanu.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".mb-3")
	List<WebElement> products; //when we put List<WebElement> it automatically understand we have to use driver.findElements
	@FindBy(css="div.ngx-spinner-overlay")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppaer(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName))
		.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppaer(toastMessage);
		waitForWebElementDisappear(spinner);
	}
			
	
	
}
