package pageObjects;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {

	// Constructor
	public LoginPage(WebDriver driver) {
		super(driver);

	}

	// Locators

	@FindBy(xpath = "//input[@placeholder='E-Mail Address']")
	WebElement emailLocator;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement passwordLocator;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginLocator;

	/*@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccountPresence;*/

	// Methods

	public void enterEmail(String email) {
		emailLocator.sendKeys(email);
	}

	public void enterPassword(String password) {
		passwordLocator.sendKeys(password);
	}
	
	public void LoginBtn() {
		loginLocator.click();
	}

	/*public String My_Account_Message_Presence() {
		try {
			return myAccountPresence.getText();
		} catch (Exception e) {
			return e.getMessage();
		}

	}*/

}
