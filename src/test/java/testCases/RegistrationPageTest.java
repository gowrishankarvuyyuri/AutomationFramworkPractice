package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseTest;

public class RegistrationPageTest extends BaseTest{

	@Test
	void Registration_Test() {
		
		log.info("In Home Page choosing Registration");
		HomePage hp = new HomePage(driver);
		hp.my_account_click();
		hp.register_click();
		log.info("Registration Opened and Placing Details");
		
		RegistrationPage rp = new RegistrationPage(driver);
		
		/*staticValues
		rp.txt_firstname("Gowri");
		rp.txt_lastname("Shankar");
		rp.txt_email("dfsdtsaddfsdtg@gmail.com");
		rp.txt_telephone("12345");
		rp.txt_password("1212");
		rp.txt_confirmpassword("1212");
		rp.btn_policy();
		rp.btn_continue();
		String actual_text = rp.text_confirm();
		*/
		
		/*randomGeneratorValues*/
		rp.txt_firstname(randomString());
		rp.txt_lastname(randomString());
		rp.txt_email(randomString() + "@yahoo.com");
		rp.txt_telephone(randomNumber());
		
		String pswd = randomAphlaNumber();
		rp.txt_password(pswd);
		rp.txt_confirmpassword(pswd);
		rp.btn_policy();
		log.info("Clicking Continue Button for Registering the User");
		rp.btn_continue();
		String actual_text = rp.text_confirm();
		log.info("Actual Text : " + actual_text);
		Assert.assertEquals(actual_text.trim(), "Your Account Has Been Created!");
	}
}
