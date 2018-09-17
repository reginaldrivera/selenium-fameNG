package famengpackage;

public class regressionPayment {
	
	public static void main (String[] args){
		
		purchaseVisa visa = new purchaseVisa();
		purchaseMastercard mastercard = new purchaseMastercard();
		purchaseAmex amex = new purchaseAmex();
		
		visa.launchBrowser();
		visa.addToBag();
		visa.checkoutPage();
		visa.paymentPage();
		visa.confirmationPage();
		visa.terminateBrowser();
		
		mastercard.launchBrowser();
		mastercard.addToBag();
		mastercard.checkoutPage();
		mastercard.paymentPage();
		mastercard.confirmationPage();
		mastercard.terminateBrowser();
		
		amex.launchBrowser();
		amex.addToBag();
		amex.checkoutPage();
		amex.paymentPage();
		amex.confirmationPage();
		amex.terminateBrowser();
	}

}
