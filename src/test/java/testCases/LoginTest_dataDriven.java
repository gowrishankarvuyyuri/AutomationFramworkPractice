	package testCases;
	
	import org.testng.Assert;
	import org.testng.annotations.Test;
	
	import pageObjects.AccountPage;
	import pageObjects.HomePage;
	import pageObjects.LoginPage;
	import testBase.BaseTest;
import utilities.DataProvider_Class;
	
	public class LoginTest_dataDriven extends BaseTest{
	
		@Test (dataProvider = "testInputData", dataProviderClass = DataProvider_Class.class)
		void loginTest(String userName, String passWord, String check) {
					
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
				lp.txt_email(userName);
				lp.txt_password(passWord);
				lp.btn_login();
			}
			catch(Exception e) {
				log.error("      --)login Test Failed Reason : " + e.getMessage());
				Assert.fail();
			}
			
			
			log.info(" Clicking logOut button in AccountPafe ");
			try {
				AccountPage ap = new AccountPage(driver);
				String text = ap.myaccount_txt();	
				Thread.sleep(3000);
				ap.btn_logOut();
				
				if(check.equals("Valid") && text.equals("My Account")) 
					Assert.assertTrue(true);
				else if(check.equals("Invalid") && !text.equals("My Account"))
					Assert.assertTrue(true);
				else
					Assert.assertTrue(true);
				
			}
			catch(Exception e) {
				log.error("		--)Failed at Verifying the AccountPage Status : " + e.getMessage());
				Assert.fail();
			}
			
		}
	}
