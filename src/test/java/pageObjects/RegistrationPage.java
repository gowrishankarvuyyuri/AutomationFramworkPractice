package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

	/*Constructor*/
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	
	/*Locators*/
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_lastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_confirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btn_policy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_continue;
	
	@FindBy(xpath="//div[@id='common-success']//div[@id='content']/h1")
	WebElement text_confirm;
	
	
	/*Actions*/
	public void txt_firstname(String name) {
		txt_firstname.sendKeys(name);
	}
	
	public void txt_lastname(String name) {
		txt_lastname.sendKeys(name);
	}
	
	public void txt_email(String name) {
		txt_email.sendKeys(name);
	}
	
	public void txt_telephone(String name) {
		txt_telephone.sendKeys(name);
	}
	
	public void txt_password(String name) {
		txt_password.sendKeys(name);
	}
	
	public void txt_confirmpassword(String name) {
		txt_confirmpassword.sendKeys(name);
	}
	
	public void btn_policy() {
		btn_policy.click();
	}
	
	public void btn_continue() {
		btn_continue.click();
	}
	
	public String text_confirm() {
		return text_confirm.getText();
	}
}
