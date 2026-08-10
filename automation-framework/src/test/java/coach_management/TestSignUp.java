package coach_management;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import listener.TestListener;
import utils.ConfigReader;
import utils.VideoRecorder;


@Listeners(TestListener.class)

public class TestSignUp {


	private String firstName = "Coach";
	private String password = "Qwerty@123", referralCode = "";
	private String brandName = "Titan Fitness Coaching";
	private String[] role = new String[] { "Gym Owner", "Fitness Coach", "Fitness Influencer", "Nutritionist/Dietitian", "Other" };
	private String countryCode = "United States";
	private String client = "50+";
	private String insta = "https://www.instagram.com", youTube = "https://www.youtube.com/";
	private String description = "Descriptions";
	private String freeTrial = "yes";
	private String tenure = "Monthly";
	private String promocode = "TESTPROMOCODE123";
	private long userName = System.currentTimeMillis();
	private boolean isClientAdd = true;
	private ConfigReader configReader;
	private String email = "coach" + userName + "@yopmail.com";
	VideoRecorder recorder;

	@DataProvider(name = "signUp", parallel = true)
	public Object[][] signupData() {
		return new Object[][] {
			{ "LAST NAME", email, "chrome" },
		};
	}

	@Test(dataProvider = "signUp", enabled = true)
	public void signUp(String lName, String emails, String browser) throws Exception {

		ChromeOptions options = new ChromeOptions();
		// Use the same CI-aware driver setup as the rest of the suite
		DriverManager.setDriver(DriverFactory.createDriverPermisson(browser, options));
		configReader = new ConfigReader();
		DriverManager.getDriver().get(configReader.getSignUpUrl());
		DriverManager.getDriver().manage().window().maximize();
		CoachSignUp coachSignUp = new CoachSignUp(DriverManager.getDriver());
		coachSignUp.signUp(firstName, lName, emails, password, referralCode);
		System.out.println("Email " + emails);
		coachSignUp.signUp2("userName" + userName, role, countryCode, client, insta, youTube);
		coachSignUp.signUp3(description);

		// Assert.assertEquals(coachSignUp.planSelect(freeTrial,"",tenure,promocode,userName+"_UserName"), email);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String testName = result.getName();

			// Guard against a null driver (e.g. if setup itself failed before
			// a session was ever created) so teardown doesn't throw its own NPE.
			if (DriverManager.getDriver() != null) {
				TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
				File src = ts.getScreenshotAs(OutputType.FILE);

				// Ensure the screenshots directory exists before writing to it
				File screenshotsDir = new File("screenshots");
				if (!screenshotsDir.exists()) {
					screenshotsDir.mkdirs();
				}

				String path = "screenshots/" + testName + ".png";
				FileUtils.copyFile(src, new File(path));
			} else {
				System.out.println("Skipping screenshot — driver was never initialized for this test.");
			}

			// recorder was never initialized anywhere in this class — guard it
			// so a missing recorder doesn't throw its own NullPointerException.
			if (recorder != null) {
				recorder.stop();
			}
		}
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}
	}
	

	
}
