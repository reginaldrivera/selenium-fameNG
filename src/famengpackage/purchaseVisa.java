package famengpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.Assert;

public class purchaseVisa {
	
	public WebDriver driver;

	public String protocol = "https://";
	public String usernamePassword = "fandpstaging:auth4fandpstaging@";
	public String baseURL = "qa1.fameandpartners.com"; //QA1 URL
	//public String baseURL = "www.fameandpartners.com"; //PRODUCTION URL
	
	public String pageURL = "/dresses/custom-dress-FPG1001";
	String driverPath = "/Users/reginaldrivera/Documents/chromedriver";

	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		if (baseURL.contentEquals("www.fameandpartners.com")){
		driver.get(protocol + baseURL + pageURL);
		}
		else{
			driver.get(protocol + usernamePassword + baseURL + pageURL);
		}
		//driver.manage().window().maximize();
	}
	
	@Test (priority = 0)
	public void addToBag(){

		// Select Height in PDP then click Add to Bag button
		driver.findElement(By.xpath("//div//div[4]//div[1]//a[1]//span[1]")).click(); //click the Change Size link
		driver.findElement(By.xpath("//div[@class='jsx-2788886246 dropdown-container']")).click(); //click height dropdown
		driver.findElement(By.xpath("//button[contains(text(),'4ft 10in')]")).click(); //select height 4ft 10 inches
		driver.findElement(By.xpath("//div[contains(text(),'US 0')]")).click(); //select size US0
		driver.findElement(By.xpath("//button[@class='jsx-960340962 button Button Button--secondary Button--fullwidth action-buttons']")).click(); //click Done
		//Verify height and size got selected
		String heightExpected = "US 0 • 4' 10\"";
		WebElement heightActual = driver.findElement(By.xpath("//div//div[4]//div[2]//div[1]//ul[1]"));	
		
		System.out.println("Verify the Size that got selected");
		System.out.println("Expected Height:  " + heightExpected);
		System.out.println("Actual Height:  " + heightActual.getText());		
		Assert.assertEquals(heightActual.getText(), heightExpected);
		
		driver.findElement(By.xpath("//button[@class='jsx-960340962 button Button Button--fullwidth add-to-cart-button']")).click(); //click Add to Bag
		
		//wait for side cart to load
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		//Verify Shopping Bag side window opened by checking that Checkout button is present		
		Boolean isPresent = driver.findElements(By.xpath("//a[@class='jsx-960340962 button Button Button--fullwidth']")).size() > 0;
		System.out.println("Does Checkout button exists?  " + isPresent);
	}
	
	@Test (priority = 1)
	public void checkoutPage(){
		
		//Click the CHECKOUT button
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[@class='jsx-960340962 button Button Button--fullwidth']")).click();
		//Construct unique Last Name by using date and time values
		String date = java.time.LocalDate.now().toString();
		String time = java.time.LocalTime.now().toString();
		String lastName = date + time;
		
		lastName = lastName.replaceAll("\\p{Punct}","");
		
		//Fill out Deliver To fields
		driver.findElement(By.xpath("//input[@id='order_ship_address_attributes_firstname']")).sendKeys("test"); //first name
		driver.findElement(By.xpath("//input[@id='order_ship_address_attributes_lastname']")).sendKeys(lastName); //last name
		driver.findElement(By.xpath("//input[@id='order_ship_address_attributes_email']")).sendKeys(lastName + "@mailinator.com"); //email
		driver.findElement(By.xpath("//input[@id='order_ship_address_attributes_phone']")).sendKeys("888-888-8888"); //phone
		
		//Fill out Delivery Address fields
		Select drpCountry = new Select(driver.findElement(By.name("order[ship_address_attributes][country_id]")));
		Select drpState = new Select(driver.findElement(By.name("order[ship_address_attributes][state_id]")));
		
		drpCountry.selectByVisibleText("United States"); //Country
		driver.findElement(By.xpath("//input[@id='order_ship_address_attributes_address1']")).sendKeys("123 Test Address"); //Street Address
		driver.findElement(By.xpath("//input[@id='order_ship_address_attributes_city']")).sendKeys("Los Angeles"); //City
		driver.findElement(By.xpath("//input[@id='order_ship_address_attributes_zipcode']")).sendKeys("90000"); //Zip Code
		drpState.selectByVisibleText("California"); //State
		WebElement alsoBilling = driver.findElement(By.xpath("//input[@id='ship_to_address']"));
		if (alsoBilling.isSelected()){
			//Click Continue to Payment button
			driver.findElement(By.xpath("//button[@name='pay_securely']")).click();
		}
		else{
			//Click Continue to Payment button
			alsoBilling.click();
			driver.findElement(By.xpath("//button[@name='pay_securely']")).click();
		}
	}
	
	@Test (priority = 2)
	public void paymentPage(){
		//Dismiss Return Insurance Popup
		WebElement insurancePopupContinue = driver.findElement(By.xpath("//button[contains(text(),'continue')]"));
		Boolean insurancePopup = driver.findElements(By.xpath("//button[contains(text(),'continue')]")).size() > 0;
		if (insurancePopup.TRUE){
			insurancePopupContinue.click();
		}
		
		//Access the Stripe iFrame and Fill out credit card info
		driver.switchTo().frame("__privateStripeFrame3");
		driver.findElement(By.name("cardnumber")).sendKeys("4242424242424242"); //test account for visa
		driver.findElement(By.name("exp-date")).sendKeys("0424");
		driver.findElement(By.name("cvc")).sendKeys("424");
		driver.findElement(By.name("postal")).sendKeys("90424");
		driver.findElement(By.name("postal")).sendKeys(Keys.TAB);
		
		//Place Your Order Now
		driver.manage().window().maximize();
		driver.switchTo().defaultContent(); //get out of Stripe iFrame
		driver.findElement(By.xpath("//button[@class='btn btn-black btn-block btn-md StripeForm__checkout-button']")).click();
	}
	
	@Test (priority = 3)
	public void confirmationPage(){
		driver.switchTo().defaultContent();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		String headerExpected = "Thanks for your order!";
		WebElement headerActual = driver.findElement(By.xpath("//h1[@class='order']"));
		//Verify Header is correct in Order Confirmation Page
		System.out.println("Expected Header text:  " + headerExpected);
		System.out.println("Actual Header text:  " + headerActual.getText());
		Assert.assertEquals(headerActual.getText(), headerExpected);		
	}
	
	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}

}
