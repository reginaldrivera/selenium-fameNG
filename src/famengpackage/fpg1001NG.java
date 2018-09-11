package famengpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;
import org.testng.Assert;


public class fpg1001NG {

	public WebDriver driver;
	public String baseURL = "https://www.fameandpartners.com";
	public String pageURL = "/dresses/custom-dress-FPG1001";
	String driverPath = "/Users/reginaldrivera/Documents/chromedriver";
	
  @BeforeTest
  public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
	  	driver.get(baseURL + pageURL);  	
  }
  
  @Test
  public void fpg1001VerifyName(){	
		// Verify the Name of FPG1001
		String dressNameExpected = "Dresses with Waistband";
		WebElement dressName = driver.findElement(By.xpath("//h1[@class='jsx-2020247591 dress-title']"));
		
		System.out.println("Verify the Name of FPG1001");
		System.out.println("Expected:  " + dressNameExpected);
		System.out.println("Actual:  " + dressName.getText().substring(0, 22) + "\n");

		Assert.assertEquals(dressName.getText().substring(0, 22), dressNameExpected);
  }
  
  @Test
  public void fpg1001VerifyPrice(){
		// Verify the Price of FPG1001
		String dressPriceExpected = "$199";
		WebElement dressPrice = driver.findElement(By.xpath("//em[@class='jsx-2020247591']"));
		
		System.out.println("Verify the Price of FPG1001");
		System.out.println("Expected:  " + dressPriceExpected);
		System.out.println("Actual:  " + dressPrice.getText() + "\n");

		Assert.assertEquals(dressPrice.getText(), dressPriceExpected);
  }
  
  @AfterTest
  public void terminaateBrowser(){	
		driver.close();
  }
}
