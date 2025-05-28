package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	//constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	//Locators
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;

	@FindBy(xpath="//input[@value='Login']")
	WebElement btn_login;
	
	
	//Actions
	public void txt_email(String email) {
		txt_email.sendKeys(email);
	}
	
	public void txt_password(String pass) {
		txt_password.sendKeys(pass);
	}
	
	public void btn_login() {
		btn_login.click();
	}
}
