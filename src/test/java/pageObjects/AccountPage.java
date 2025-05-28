package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{

	//constructor
	public AccountPage(WebDriver driver) {
		super(driver);
	}
	
	
	//locators
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement myaccount_txt;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btn_logOut;
	
	
	//Actions
	
	public String myaccount_txt() {
		return myaccount_txt.getText();
	}
	
	public void btn_logOut() {
		btn_logOut.click();
	}
}
