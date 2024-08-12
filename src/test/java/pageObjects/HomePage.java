package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	//Constructor to initialize the driver
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	//Locators
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement clickMyAccountLoc;
	
	@FindBy(xpath="//*[text()='Register']")
	WebElement clickRegLoc;
	
	@FindBy(xpath="//*[text()='Login']")
	WebElement LoginLocator;
	
	//Test
	public void clickMyAccount() {
		clickMyAccountLoc.click();
		
	}
	
	public void clickRegister() {
		
		clickRegLoc.click();
	}
	
	public void Login_Method() {
		LoginLocator.click();
	}
	
	
	

}
