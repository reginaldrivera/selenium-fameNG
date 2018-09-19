package famengpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.Assert;

public class login {
	
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
		driver.findElement(By.xpath(globalVars.btnLoginLogin)).click(); //click the Join button without typing any credentials
		String expectedError = "Your email and/or password is incorrect";
		String actualError = driver.findElement(By.xpath("//p[@class='error']")).getText();
		
		Assert.assertEquals(actualError, expectedError);
		
		driver.navigate().refresh(); //refresh page after verification passes
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	}

}
