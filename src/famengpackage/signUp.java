package famengpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.Assert;

public class signUp {

	public WebDriver driver;
	public String pageURL = "/account/signup";
	String driverPath = globalVars.chromePath;
	
	@BeforeTest
	public void launchBrowser(){
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		//QA Environment
		driver.get(globalVars.protocol + globalVars.usernamePassword + globalVars.qaBaseURL + pageURL);
	}
	
	@Test (priority = 0)
	public void loginNoCredentials(){
		//driver.findElement(By.xpath(xpathExpression)).click; //click the Signup button without typing any credentials
		
		String expectedError = "";
		
	}
}
