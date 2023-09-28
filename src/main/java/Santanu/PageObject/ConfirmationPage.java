package Santanu.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santanu.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	By confirmationMessage = By.cssSelector(".hero-primary");
	
	public String getConfirmationMessage() {
		waitForElementToAppaer(confirmationMessage);
		return confirmMessage.getText().toUpperCase();
	}
}
