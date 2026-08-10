package coach_management;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import utils.ConfigReader;

public class LoginCoachTest {

	WebDriver driver;
	private LoginCoach lc;
	private	ConfigReader configReader;

	@BeforeTest
	public void openUrl ()
	{
		configReader = new ConfigReader();

		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 1);
		options.setExperimentalOption("prefs", prefs);

		// Pass options into driver creation — single driver instance
		DriverManager.setDriver(DriverFactory.createDriverPermisson(configReader.getBrowser(), options));

		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().get(configReader.getSignInUrl());

		this.driver = DriverManager.getDriver();
		lc = new LoginCoach(this.driver);
	}
	@Test 
	public void loginCoach() throws InterruptedException
	{
		String actualResult=lc.loginCoach("testing1137@yopmail.com", "Qwerty@123");
		Assert.assertEquals(actualResult, "Coach logged in successfully");
	}
	 @AfterTest
	    public void closeBrowser() throws IOException
	    {

	    	DriverManager.getDriver().quit();

	    }
}
