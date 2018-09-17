package famengpackage;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;
import org.testng.Assert;

public class fpg1001NG {

	public WebDriver driver;
	
	public String pageURL = "/dresses/custom-dress-FPG1001";
	String driverPath = globalVars.chromePath;

	@BeforeTest
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		//PROD
		driver.get(globalVars.protocol + globalVars.prodBaseURL + pageURL);
		//QA
		//driver.get(globalVars.protocol + globalVars.usernamePassword + globalVars.qaBaseURL + pageURL);
	}

	@Test
	public void fpg1001VerifyName() {
		// Verify the Name of FPG1001
		String dressNameExpected = "Dresses with Waistband";
		WebElement dressName = driver.findElement(By.xpath("//h1[@class='jsx-2020247591 dress-title']"));

		System.out.println("Verify the Name of FPG1001");
		System.out.println("Expected:  " + dressNameExpected);
		System.out.println("Actual:  " + dressName.getText().substring(0, 22) + "\n");

		Assert.assertEquals(dressName.getText().substring(0, 22), dressNameExpected);
	}

	@Test
	public void fpg1001VerifyPrice() {
		// Verify the Price of FPG1001
		String dressPriceExpected = "$199";
		WebElement dressPrice = driver.findElement(By.xpath("//em[@class='jsx-2020247591']"));

		System.out.println("Verify the Price of FPG1001");
		System.out.println("Expected:  " + dressPriceExpected);
		System.out.println("Actual:  " + dressPrice.getText() + "\n");

		Assert.assertEquals(dressPrice.getText(), dressPriceExpected);
	}

	@Test
	public void fpg1001VerifyAddToBag() {
		// Verify Add to Bag button
		String addToBagLabel = "ADD TO BAG";
		WebElement addToBag = driver.findElement(
				By.xpath("//button[@class='jsx-960340962 button Button Button--fullwidth add-to-cart-button']"));

		System.out.println("Verify Add to Bag button");
		System.out.println("Expected label:  " + addToBagLabel);
		System.out.println("Actual label:  " + addToBag.getText() + "\n");

		Assert.assertEquals(addToBag.getText(), addToBagLabel);
	}

	@Test
	public void fpg1001VerifyShareDesignLink() {
		// Verify Share your design Link
		String shareYourDesignLabel = "Share your design";
		WebElement shareYourDesign = driver
				.findElement(By.xpath("//a[@class='jsx-2020247591 no-underline icon-text']"));

		System.out.println("Verify Share your design Link");
		System.out.println("Expected label:  " + shareYourDesignLabel);
		System.out.println("Actual label:  " + shareYourDesign.getText() + "\n");

		Assert.assertEquals(shareYourDesign.getText(), shareYourDesignLabel);
	}

	@Test
	public void fpg1001VerifyOrderFabricSwatchesLink() {
		// Verify Order Fabric Swatches Link
		String orderFabricSwatchesLabel = "Order Fabric Swatches";
		String orderFabricSwatchesLink = "https://www.fameandpartners.com/custom-clothes/order-swatches";
		WebElement orderFabricSwatches = driver.findElement(By.xpath("//a[@class='no-underline icon-text']"));
		String orderFabricSwatchesURL = driver.findElement(By.linkText("Order Fabric Swatches")).getAttribute("href");

		System.out.println("Verify Order Fabric Swatches Link");
		System.out.println("Expected label:  " + orderFabricSwatchesLabel);
		System.out.println("Actual label:  " + orderFabricSwatches.getText() + "\n");

		Assert.assertEquals(orderFabricSwatches.getText(), orderFabricSwatchesLabel);

		System.out.println("Expected URL:  " + orderFabricSwatchesLink);
		System.out.println("Actual URL:  " + orderFabricSwatchesURL + "\n");

		Assert.assertEquals(orderFabricSwatchesURL, orderFabricSwatchesLink);
	}

	@Test
	public void fpg1001VerifyDeliveryShippingCopy() {
		// Verify Delivery and Shipping copy
		String deliveryText = "Estimated Delivery 6-10 weeks. ";
		String shippingText = "Shipping is free on your customized item. Learn more";
		String deliveryShippingText = deliveryText + shippingText;
		WebElement deliveryShipping = driver.findElement(By.xpath("//p[@class='jsx-2020247591']"));

		System.out.println("Verify Delivery and Shipping copy");
		System.out.println("Expected text:  " + deliveryShippingText);
		System.out.println("Actual text:  " + deliveryShipping.getText() + "\n");

		Assert.assertEquals(deliveryShipping.getText(), deliveryShippingText);
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}
}
