package Santanu.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santanu.AbstractComponents.AbstractComponent;


//The whole page is know as pageobject model which only focus on locators and actions
public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); //here this refers class variable
	}
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	/* here both 14 and 17 line are same but in 17th line we called it thorugh FindBY annotation which is known as 
	 * PageFactory
	 */
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passWord;
	
	@FindBy(css="input[id='login']")
	WebElement submit;
	
	@FindBy(css="div[role='alert']")
	WebElement errorMessage;
	
	//this method know as Action method
	public ProductCatalogue loginApplication(String email, String password){
		userEmail.sendKeys(email);
		passWord.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppaer(errorMessage);
		return errorMessage.getText();
	}
}
