package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import net.bytebuddy.utility.RandomString;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void AccRegTest() {
        logger.info("***********Initialized logs_ TC001_AccountRegistrationTest**************");

        try {
            HomePage homepage = new HomePage(driver);
            homepage.clickMyAccount();

            logger.info("Click on My account");
            homepage.clickRegister();

            logger.info("Click on registered link");

            Random random = new Random();

            AccountRegistrationPage ARPage = new AccountRegistrationPage(driver);

            logger.info("Providing customer details");
            ARPage.SetFirstName(RandomString().toUpperCase());
            ARPage.SetLastName("Sharma");
            ARPage.setEmail(RandomString().toLowerCase() + "@gmail.com");

            //ARPage.setTelephone(RandomNumbers());
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.offsetHeight)");

            String password = RandomAlphaNumeric();
            ARPage.setPassword(password);
// Please Enable when you will use this site : https://tutorialsninja.com/demo/index.php?route=common/home
            //ARPage.confirmPass(password);
            ARPage.PrivacyPolicy();
            Thread.sleep(2000);
            ARPage.ContinueBtn();

            logger.info("Validating expected message------");
            String msgConfirm = ARPage.MsgConfirmation();

            // We intentionally failing below test case to check the debug logs
            if (msgConfirm.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            } else {
                logger.error("Test failed");
                logger.debug("Debug logs");
                Assert.assertTrue(false);

            }

            // Assert.assertEquals(msgConfirm, "Your Account Has Been Created!", "Msg is not mathed");
        } catch (Exception e) {
            /*logger.error("Test Failed");
            logger.debug("Debug logs");*/
            Assert.fail("Test is failed");

        }

        logger.info("***********Finished TC_ TC001_AccountRegistrationTest**************");
    }
}