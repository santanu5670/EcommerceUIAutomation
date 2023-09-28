package Santanu.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Santanu.PageObject.CartPage;
import Santanu.PageObject.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".btn.btn-custom  .fa.fa-shopping-cart")
	WebElement cartHeader;
	
	@FindBy(css=".btn.btn-custom  .fa.fa-handshake-o")
	WebElement orderHeader;
	
	public void waitForElementToAppaer(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppaer(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForWebElementDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void scrollToViewWebElement(WebElement element) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(2000);
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}
}
