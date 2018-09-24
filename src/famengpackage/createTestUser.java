package famengpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.Assert;

public class createTestUser {

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
	
	@Test
	public void createUser(){
		driver.findElement(By.xpath(globalVars.txtboxSignUpFirstName)).sendKeys(globalVars.testFirstName);
		driver.findElement(By.xpath(globalVars.txtboxSignUpLastName)).sendKeys(globalVars.testLastName);
		driver.findElement(By.xpath(globalVars.txtboxSignUpEmail)).sendKeys(globalVars.testEmail);
		driver.findElement(By.xpath(globalVars.txtboxSignUpPassword)).sendKeys(globalVars.password); 
		driver.findElement(By.xpath(globalVars.btnSignUpJoin)).click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a[@class='jsx-3861230406 UserMenu__Trigger no-underline']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath(globalVars.txtboxProfileLastName)).getAttribute("value"), "James");
		Assert.assertEquals(driver.findElement(By.xpath(globalVars.txtboxProfileEmail)).getAttribute("value"), "showtime23@mailinator.com");
		
	}
	
	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}

}
