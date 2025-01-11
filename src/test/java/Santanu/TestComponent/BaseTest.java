package Santanu.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Santanu.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initilizeDriver() throws IOException {
		Properties prop = new Properties();
		/* System.getProperty("user.dir") use to retrive system path and using // java recognize as path */
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Santanu//Resources//GlobalData.properties");
		prop.load(fis);
		String browsername = prop.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
//			WebDriverManager.chromedriver().driverVersion("116.0.5845.96").setup();
			driver = new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		
		//String to HashMap using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" +testCaseName+ ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" +testCaseName+ ".png";
	}
	/*
	 * We use alwaysRun=true because when we use groups then beforemethod and aftermethod not running and to avoid 
	 * this dependency we use alwaysRun=true. Never use group on beforemethod and aftermethod annotaion if we use 
	 * it then we restrict BeforeMethod and Aftermethod on particular group only
	 */
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplications() throws IOException {
		driver = initilizeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
