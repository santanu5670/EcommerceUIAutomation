package Santanu.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santanu.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@class='input txt']")
	List<WebElement> user_details; //cvv, name
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By countryDropdown = By.cssSelector("section.ta-results");
	
	public void checkOutDetails(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys((user_details).get(0), "123");
		a.sendKeys((user_details).get(1), "Sanjay");
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppaer(countryDropdown);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
}
