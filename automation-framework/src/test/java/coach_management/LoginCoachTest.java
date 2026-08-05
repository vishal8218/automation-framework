package coach_management;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginCoachTest {

	WebDriver driver;
	private LoginCoach lc;
	@BeforeTest
	public void openUrl ()
	{
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<>();

		prefs.put(
		    "profile.default_content_setting_values.notifications",
		    1
		);

		options.setExperimentalOption("prefs", prefs);

		 driver = new ChromeDriver(options);
	  driver.get("https://beta.btrainr.com/login");
	 driver.manage().window().maximize();
	  lc=new  LoginCoach(this.driver);
	}
	
	@Test 
	public void loginCoach() throws InterruptedException
	{
		String actualResult=lc.loginCoach("testing1137@yopmail.com", "Qwerty@123");
		Assert.assertEquals(actualResult, "Coach logged in successfully");
		lc.changeEmail();
	}
}
