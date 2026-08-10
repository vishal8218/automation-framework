package usermangement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import usermanagement.AddLeadWeb;
import utils.ConfigReader;

public class AddLeadFromWeb {
	private WebDriver driver;
	private ConfigReader configReader;
	private String browser = "chrome";
	private long userName = System.currentTimeMillis();
	private String email = "user" + userName + "@yopmail.com";
	private String fName = "firstName";
	private String lName = "lastName";
	private String phone = "9870133899";
	private int age = 35;
	private int height = 172, weight = 90;
	private String goals[] = new String[] { "Loose Weight", "Build Muscle" };
	private String health[] = new String[] { "Allergies", "Chronic Disease", "Mental Health" };
	private String diet = "veg";

	@BeforeTest
	public void openBrowser() {
		this.configReader = new ConfigReader();
		ChromeOptions options = new ChromeOptions();

		// Use the same CI-aware driver setup as the rest of the suite
		DriverManager.setDriver(DriverFactory.createDriverPermisson(browser, options));
		this.driver = DriverManager.getDriver();
		this.driver.manage().window().maximize();
		this.driver.get(configReader.getCoachPortfolioLink());
	}

	@Test
	public void addLead() throws InterruptedException {
		AddLeadWeb addLeadWeb = new AddLeadWeb(this.driver);
		addLeadWeb.basicDetail(phone, fName, lName, email);
		addLeadWeb.enterPhysicalDetail("Female", age, "NA");
		addLeadWeb.enterPhysicalDetail(height, weight, goals);
		String url = addLeadWeb.enterDietPreferences(diet, health);
		Assert.assertEquals(url, configReader.getCoachPortfolioLink());
	}

	@AfterTest
	public void closeBrowser() {
		// Was calling .quit() twice (once directly, once via DriverManager) —
		// quitting an already-closed session throws an error. Quit once.
		DriverManager.getDriver().quit();
	}
}
