package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups= {"Regression","Master"})
	public void Login() {

		logger.info("**************TC002_LoginTest begins********************");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked On My Account");
		hp.Login_Method();
		logger.info("Clicked On Login Button");
		LoginPage lp = new LoginPage(driver);

		lp.enterEmail(pr.getProperty("email"));
		lp.enterPassword(pr.getProperty("password"));

		lp.LoginBtn();

		MyAccountPage myAccount = new MyAccountPage(driver);
		boolean targetPage=myAccount.My_Account_Message_Presence();
		Assert.assertEquals(targetPage, true,"Login Failed");

		logger.info("**************TC002_LoginTest Finished*******************");
		}catch(Exception e) {
			logger.debug("debug logs");
			logger.error("Error Logs");
			Assert.fail();
		}
	}

}
