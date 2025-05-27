package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	/*Using PageFactory*/
	
	
	/*constructor*/
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	/*locators*/
	@FindBy(xpath="(//span[normalize-space()='My Account'])[1]") 
	WebElement my_account_click;
	@FindBy(xpath="//a[normalize-space()='Register']") 
	WebElement register_click;	
	
	
	/*Acrtions*/
	public void my_account_click() {
		my_account_click.click();
	}
	
	public void register_click() {
		register_click.click();
	}
}
