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
	public void signupNoCredentials(){
		driver.findElement(By.xpath(globalVars.btnSignUpJoin)).click(); //click the Join button without typing any credentials
		String expectedError = "Check that you've filled in all fields and your email is correct";
		String actualError = driver.findElement(By.xpath("//p[@class='error']")).getText();
		
		Assert.assertEquals(actualError, expectedError);
		
		driver.navigate().refresh(); //refresh page after verification passes
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	}
	
	@Test (priority = 1)
	public void signupFirstNameOnly(){
		driver.findElement(By.xpath(globalVars.txtboxSignUpFirstName)).sendKeys(globalVars.firstname); //type first name only
		driver.findElement(By.xpath(globalVars.btnSignUpJoin)).click(); //click the Join button
		String expectedError = "Check that you've filled in all fields and your email is correct";
		String actualError = driver.findElement(By.xpath("//p[@class='error']")).getText();
		
		Assert.assertEquals(actualError, expectedError);
		
		driver.navigate().refresh(); //refresh page after verification passes	
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	}
	
	@Test (priority = 2)
	public void signupLastNameOnly(){
		driver.findElement(By.xpath(globalVars.txtboxSignUpLastName)).sendKeys(globalVars.lastName); //type last name only
		driver.findElement(By.xpath(globalVars.btnSignUpJoin)).click(); //click the Join button
		String expectedError = "Check that you've filled in all fields and your email is correct";
		String actualError = driver.findElement(By.xpath("//p[@class='error']")).getText();
		
		Assert.assertEquals(actualError, expectedError);
		
		driver.navigate().refresh(); //refresh page after verification passes		
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	}
	
	@Test (priority = 3)
	public void loginEmailOnly(){
		driver.findElement(By.xpath(globalVars.txtboxSignUpEmail)).sendKeys(globalVars.email); //type email only
		driver.findElement(By.xpath(globalVars.btnSignUpJoin)).click(); //click the Join button
		String expectedError = "Check that you've filled in all fields and your email is correct";
		String actualError = driver.findElement(By.xpath("//p[@class='error']")).getText();
		
		Assert.assertEquals(actualError, expectedError);
		
		driver.navigate().refresh(); //refresh page after verification passes		
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	}
	
	@Test (priority = 4)
	public void signupPasswordOnly(){
		driver.findElement(By.xpath(globalVars.txtboxSignUpPassword)).sendKeys(globalVars.password); //type password only
		driver.findElement(By.xpath(globalVars.btnSignUpJoin)).click(); //click the Join button
		String expectedError = "Check that you've filled in all fields and your email is correct";
		String actualError = driver.findElement(By.xpath("//p[@class='error']")).getText();
		
		Assert.assertEquals(actualError, expectedError);
		
		driver.navigate().refresh(); //refresh page after verification passes	
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	}
	
	@Test (priority = 5)
	public void signupFull(){
		driver.findElement(By.xpath(globalVars.txtboxSignUpFirstName)).sendKeys(globalVars.firstname);
		driver.findElement(By.xpath(globalVars.txtboxSignUpLastName)).sendKeys(globalVars.lastName);
		String expectedLastName = globalVars.lastName;
		driver.findElement(By.xpath(globalVars.txtboxSignUpEmail)).sendKeys(globalVars.email);
		String expectedEmail = globalVars.email;
		driver.findElement(By.xpath(globalVars.txtboxSignUpPassword)).sendKeys(globalVars.password); //type password only
		driver.findElement(By.xpath(globalVars.btnSignUpJoin)).click(); //click the Join button
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a[@class='jsx-3861230406 UserMenu__Trigger no-underline']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath(globalVars.txtboxProfileLastName)).getAttribute("value"), expectedLastName);
		Assert.assertEquals(driver.findElement(By.xpath(globalVars.txtboxProfileEmail)).getAttribute("value"), expectedEmail);
		
	}
	
	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}
}
