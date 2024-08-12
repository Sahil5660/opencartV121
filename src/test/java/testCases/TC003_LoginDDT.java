package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void LoginDDTTC(String email, String pwd, String exp) {

		logger.info("**************TC003_LoginDDTTest begins********************");
        try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked On My Account");
		hp.Login_Method();
		logger.info("Clicked On Login Button");
		LoginPage lp = new LoginPage(driver);
        lp.enterEmail(email);
		lp.enterPassword(pwd);
        lp.LoginBtn();
		

		MyAccountPage myAccount = new MyAccountPage(driver);
		boolean targetPage = myAccount.My_Account_Message_Presence();

		if (exp.equalsIgnoreCase("Valid")) {
			if (targetPage == true) {
				myAccount.logout_Method();
				Assert.assertTrue(true, "Login is success");
			} else {
				Assert.assertTrue(false, "Login is failed");
			}
		}
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				myAccount.logout_Method();
				Assert.assertTrue(true, "Test is failed");
			} else {
				Assert.assertTrue(false, "Test is passed");
			}
		}
	}
        catch(Exception e) {
        	
        	logger.debug("Debug logs");
        	logger.error("Error logs");
        	Assert.fail("Test is failed");
        }
	}
}
