package famengpackage;
//import java.util.concurrent.TimeUnit;

public class globalVars {
	//page attributes
	public static String chromePath = "/Users/reginaldrivera/Documents/chromedriver";
	public static String protocol = "https://";
	public static String usernamePassword = "fandpstaging:auth4fandpstaging@";
	public static String qaBaseURL = "qa4.fameandpartners.com"; //QA1 URL
	public static String prodBaseURL = "www.fameandpartners.com"; //PRODUCTION URL
	
	//account information
	public static String date = java.time.LocalDate.now().toString();
	public static String time = java.time.LocalTime.now().toString();
	public static String constructLastName = date + time;
	
	public static String firstname = "test";
	public static String lastName = constructLastName.replaceAll("\\p{Punct}","");
	public static String email = lastName + "@mailinator.com";
	public static String phone = "8888888888";
	public static String password = "Test1234";
	
	//address information
	public static String streetAddress = "123 Test Address";
	public static String streetAddress2 = "Unit A";
	public static String city = "Los Angeles";
	public static String state = "California";
	public static String zipcode = "90000";
	
	//payment information
	public static String ccVisa = "4242424242424242";
	public static String ccVisaDebit = "4000056655665556";
	public static String ccMasterCard = "5555555555554444";
	public static String ccMasterCardDebit = "5200828282828210";
	public static String ccAmex = "378282246310005";
	public static String ccDiscover = "6011111111111117";
	public static String ccExpDate = "424";
	public static String ccCVC = "424";
	public static String ccCVCAmex = "4242";
	public static String ccZipCode = "90424";
	
	//buttons
	public static String btnSideCartCheckout = "//a[@class='jsx-960340962 button Button Button--fullwidth']";
	public static String btnCheckoutContinueToPayment = "//button[@name='pay_securely']";
	public static String btnPaymentPlaceOrder = "//button[@class='btn btn-black btn-block btn-md StripeForm__checkout-button']";
	public static String btnSignUpJoin = "//button[@class='jsx-1369247218 button Button Button--fullwidth']";
	public static String btnLoginLogin = "//button[@class='jsx-1369247218 button Button Button--fullwidth']";
	
	//textboxes - Checkout page
	public static String txtboxCheckoutFirstName = "//input[@id='order_ship_address_attributes_firstname']";
	public static String txtboxCheckoutLastName = "//input[@id='order_ship_address_attributes_lastname']";
	public static String txtboxCheckoutEmail = "//input[@id='order_ship_address_attributes_email']";
	public static String txtboxCheckoutPhone = "//input[@id='order_ship_address_attributes_phone']";
	public static String txtboxCheckoutStreetAddress = "//input[@id='order_ship_address_attributes_address1']";
	public static String txtboxCheckoutStreetAddress2 = "";
	public static String txtboxCheckoutCity = "//input[@id='order_ship_address_attributes_city']";
	public static String drpdownCheckoutState = "order[ship_address_attributes][state_id]";
	public static String drpdownCheckoutCountry = "order[ship_address_attributes][country_id]";
	public static String txtboxCheckoutZipCode = "//input[@id='order_ship_address_attributes_zipcode']";
	
	//textboxes - signup page
	public static String txtboxSignUpFirstName = "//input[@placeholder='First Name']";
	public static String txtboxSignUpLastName = "//input[@placeholder='Last Name']";
	public static String txtboxSignUpEmail = "//input[@placeholder='Email']";
	public static String txtboxSignUpPassword = "//input[@placeholder='Password']";
	
	//textboxes - Profile page
	public static String txtboxProfileFirstName = "";
	public static String txtboxProfileLastName = "//input[@id='profile_last_name']";
	public static String txtboxProfileEmail = "//input[@id='profile_email']";
	
	
	
}
