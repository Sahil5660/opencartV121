package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.batik.util.Platform;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public Properties pr;
	public WebDriverWait wait;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {
		FileReader fr = new FileReader("../OpenCartV121/src/test/resources/config.properties");
		pr = new Properties();
		pr.load(fr);

		logger = LogManager.getLogger(this.getClass());
       
		
		//This is when we have to run our TCs in the VM by using selenium grid library
		if (pr.getProperty("execution_env").equalsIgnoreCase("Remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			
            } else if(os.equalsIgnoreCase("linux")) {
            	cap.setPlatform(Platform.LINUX);
            }
			
			
			else {
				System.out.println("Invalid platform defined");
				return;
			}

			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;

			case "firefox":
				cap.setBrowserName("firefox");
				break;

			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;

			default:
				System.out.println("Invalid browser");
				return;
			}
         driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}

		if (pr.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser");
				return;

			}

		}
		driver.get(pr.getProperty("appURL2"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='My Account']")));
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void teardown() {
		driver.close();
	}

	public String RandomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;

	}

	public String RandomNumbers() {
		String generatedNumber = RandomStringUtils.randomNumeric(5);
		return generatedNumber;
	}

	// Mixer of above 2
	public String RandomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(5);

		return (generatedString + generatedNumber);
	}

	public WebElement waitForElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeClickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}
}

// public void waitForPage