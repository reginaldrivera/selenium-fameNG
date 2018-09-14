package famengpackage;
//import java.util.concurrent.TimeUnit;

public class globalVars {
	//page attributes
	public static String protocol = "https://";
	public static String usernamePassword = "fandpstaging:auth4fandpstaging@";
	public static String qaBaseURL = "qa1.fameandpartners.com"; //QA1 URL
	public static String prodBaseURL = "www.fameandpartners.com"; //PRODUCTION URL
	
	//account information
	public static String date = java.time.LocalDate.now().toString();
	public static String time = java.time.LocalTime.now().toString();
	public static String constructLastName = date + time;
	
	public static String firstname = "test";
	public static String lastName = constructLastName.replaceAll("\\p{Punct}","");
	public static String email = lastName + "@mailinator.com";
	public static String phone = "8888888888";
	
	//address information
	public static String streetAddress = "123 Test Address";
	public static String streetAddress2 = "Unit A";
	public static String city = "Los Angeles";
	public static String state = "California";
	public static String zipcode = "90000";
	
	//payment information
	public static String ccVisa = "4242424242424242";
	public static String ccExpDate = "424";
	public static String ccCVC = "424";
	public static String ccZipCode = "90424";
	
	//buttons
	public static String btnSideCartCheckout = "//a[@class='jsx-960340962 button Button Button--fullwidth']";
	public static String btnCheckoutContinueToPayment = "//button[@name='pay_securely']";
	public static String btnPaymentPlaceOrder = "//button[@class='btn btn-black btn-block btn-md StripeForm__checkout-button']";

	

}
