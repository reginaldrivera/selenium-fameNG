package famengpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.Assert;

public class login {
	
	public WebDriver driver;
	public String pageURL = "/account/login";
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
	
	@Test (priority = 1)
	public void loginWrongPassword(){
		driver.findElement(By.xpath(globalVars.txtboxSignUpEmail)).sendKeys(globalVars.testEmail);
		driver.findElement(By.xpath(globalVars.txtboxSignUpPassword)).sendKeys("bleh");
		driver.findElement(By.xpath(globalVars.btnLoginLogin)).click();
		String expectedError = "Your email and/or password is incorrect";
		String actualError = driver.findElement(By.xpath("//p[@class='error']")).getText();
		
		Assert.assertEquals(actualError, expectedError);
		
		driver.navigate().refresh(); //refresh page after verification passes
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	}
	
	@Test (priority = 2)
	public void loginSuccessful(){
		driver.findElement(By.xpath(globalVars.txtboxSignUpEmail)).sendKeys(globalVars.testEmail);
		driver.findElement(By.xpath(globalVars.txtboxSignUpPassword)).sendKeys(globalVars.password);
		driver.findElement(By.xpath(globalVars.btnLoginLogin)).click(); 
		String expectedLastName = globalVars.testLastName;
		String expectedEmail = globalVars.testEmail;
		
		driver.findElement(By.xpath("//a[@class='jsx-3861230406 UserMenu__Trigger no-underline']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath(globalVars.txtboxProfileLastName)).getAttribute("value"), expectedLastName);
		Assert.assertEquals(driver.findElement(By.xpath(globalVars.txtboxProfileEmail)).getAttribute("value"), expectedEmail);
		
	}
	
	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}

}
