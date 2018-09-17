package famengpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.testng.Assert;

public class purchaseVisa {
	
	public WebDriver driver;

	public String pageURL = "/dresses/custom-dress-FPG1001";
	String driverPath = globalVars.chromePath;

	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		//PROD
		//driver.get(globalVars.protocol + globalVars.prodBaseURL + pageURL);
		//QA
		driver.get(globalVars.protocol + globalVars.usernamePassword + globalVars.qaBaseURL + pageURL);
		
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
		driver.findElement(By.xpath(globalVars.btnSideCartCheckout)).click();
		
		//Fill out Deliver To fields
		driver.findElement(By.xpath(globalVars.txtboxCheckoutFirstName)).sendKeys(globalVars.firstname + "_visa"); //first name
		driver.findElement(By.xpath(globalVars.txtboxCheckoutLastName)).sendKeys(globalVars.lastName); //last name
		driver.findElement(By.xpath(globalVars.txtboxCheckoutEmail)).sendKeys(globalVars.email); //email
		driver.findElement(By.xpath(globalVars.txtboxCheckoutPhone)).sendKeys(globalVars.phone); //phone
		
		//Fill out Delivery Address fields
		Select drpCountry = new Select(driver.findElement(By.name(globalVars.drpdownCheckoutCountry)));
		Select drpState = new Select(driver.findElement(By.name(globalVars.drpdownCheckoutState)));
		
		drpCountry.selectByVisibleText("United States"); //Country
		driver.findElement(By.xpath(globalVars.txtboxCheckoutStreetAddress)).sendKeys(globalVars.streetAddress); //Street Address
		driver.findElement(By.xpath(globalVars.txtboxCheckoutCity)).sendKeys(globalVars.city); //City
		driver.findElement(By.xpath(globalVars.txtboxCheckoutZipCode)).sendKeys(globalVars.zipcode); //Zip Code
		drpState.selectByVisibleText(globalVars.state); //State
		WebElement alsoBilling = driver.findElement(By.xpath("//input[@id='ship_to_address']"));
		if (alsoBilling.isSelected()){
			//Click Continue to Payment button
			driver.findElement(By.xpath(globalVars.btnCheckoutContinueToPayment)).click();
		}
		else{
			//Click Continue to Payment button
			alsoBilling.click();
			driver.findElement(By.xpath(globalVars.btnCheckoutContinueToPayment)).click();
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
		driver.findElement(By.name("cardnumber")).sendKeys(globalVars.ccVisa); //test account for visa
		driver.findElement(By.name("exp-date")).sendKeys(globalVars.ccExpDate);
		driver.findElement(By.name("cvc")).sendKeys(globalVars.ccCVC);
		driver.findElement(By.name("postal")).sendKeys(globalVars.ccZipCode);
		driver.findElement(By.name("postal")).sendKeys(Keys.TAB);
		
		//Place Your Order Now
		driver.manage().window().maximize();
		driver.switchTo().defaultContent(); //get out of Stripe iFrame
		driver.findElement(By.xpath(globalVars.btnPaymentPlaceOrder)).click();
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
