package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseTest;

public class LoginTest extends BaseTest{

	@Test(groups = {"sanity","master"}, alwaysRun = true)
	void loginTest() {
		
		log.info(" Login Test Case Starts ");
		
		
		log.info(" Performing Actions on Home Page for loginTest ");
		try {
			HomePage hp = new HomePage(driver);
			hp.my_account_click();
			hp.login_click();
		}
		catch(Exception e) {
			log.error(" 	--)Navigating to logIn failed Reason : " + e.getMessage());
		}
		
		
		log.info(" Performing Actions at loginTest ");
		try {
			LoginPage lp = new LoginPage(driver);
			lp.txt_email(prop.getProperty("login_username"));
			lp.txt_password(prop.getProperty("login_password"));
			lp.btn_login();
			Thread.sleep(3000);
		}
		catch(Exception e) {
			log.error("      --)login Test Failed Reason : " + e.getMessage());
			Assert.fail();
		}
		
		
		log.info(" Clicking logOut button in AccountPafe ");
		try {
			AccountPage ap = new AccountPage(driver);
			String text = ap.myaccount_txt();	
			ap.btn_logOut();
			Assert.assertEquals(text, "My Account");
		}
		catch(Exception e) {
			log.error("		--)Failed at Verifying the AccountPage Status : " + e.getMessage());
			Assert.fail();
		}
		
	}
}
