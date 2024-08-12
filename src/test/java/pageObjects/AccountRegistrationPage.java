package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	// This is a constructor to initialize the drivers to control the web browser
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	// Now create locator

	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement FirstNameInputBoxLoc;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement LastNameInputBoxLoc;

	@FindBy(xpath = "//input[@placeholder='E-Mail']")
	WebElement EmailTextBoxLoc;
	
	@FindBy(xpath="//input[@placeholder='Telephone']")
	WebElement TelePhoneLoc;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement PasswordTextBoxLoc;
	
	@FindBy(xpath="//input[@placeholder='Password Confirm']")
	WebElement confirmPasswordLocator;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement PolicyScrollerLoc;
    //use when use ninja site(https://tutorialsninja.com) : //input[@value='Continue']
	@FindBy(xpath = "//*[text()='Continue']")
	WebElement ConitnueBtnLoc;

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	// Create Actions Now

	public void SetFirstName(String FirstName) {
		FirstNameInputBoxLoc.sendKeys(FirstName);

	}

	public void SetLastName(String LastName) {
		LastNameInputBoxLoc.sendKeys(LastName);

	}

	public void setEmail(String Email) {
		EmailTextBoxLoc.sendKeys(Email);
	}
	
	public void setTelephone(String Telephone) {
		TelePhoneLoc.sendKeys(Telephone);
	}
	

	public void setPassword(String Password) {
		PasswordTextBoxLoc.sendKeys(Password);
	}
	
	public void confirmPass(String cpassword) {
		confirmPasswordLocator.sendKeys(cpassword);
	}

	public void PrivacyPolicy() {
		PolicyScrollerLoc.click();
	}

	public void ContinueBtn() {
		ConitnueBtnLoc.click();
	}

	public String MsgConfirmation() {

		try {
			return msgConfirmation.getText();
		}

		catch (Exception e) {
			return (e.getMessage());
		}
	}

}
