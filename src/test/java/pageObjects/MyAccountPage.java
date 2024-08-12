package pageObjects;

import static org.testng.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class MyAccountPage extends BasePage {

	// Constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);

	}

	// Locator

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccountPresence;
	
	@FindBy(xpath="(//*[text()='Logout'])[2]")
	WebElement logoutLocator;

	
	// Methods

	public boolean My_Account_Message_Presence() {
		try {
			return myAccountPresence.isDisplayed();

		}

		catch (Exception e) {
			return false;
		}

	}
	
	public void logout_Method() {
		logoutLocator.click();
	}

}
