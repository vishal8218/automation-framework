package usermangement;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import usermanagement.AddClientWeb;
import utils.ConfigReader;

public class TestAddClient {

	private WebDriver driver;
	private ConfigReader configReader;
	private String browser = "chrome";
	private long userName = System.currentTimeMillis();
	private String email = "user" + userName + "@yopmail.com";
	private String fName = "firstName";
	private String lName = "lastName";
	private String phone = "98701282904";
	private int age = 35;
	private int height = 172, weight = 90;
	private String goals[] = new String[] { "Loose Weight", "Build Muscle" };

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
	public void addClient() throws InterruptedException, IOException {
		AddClientWeb addClientWeb = new AddClientWeb(this.driver);
		addClientWeb.basicDetail(phone, fName, lName, email);
		addClientWeb.enterPhysicalDetail("female", age, "veg");
		addClientWeb.enterPhysicalDetail(height, weight, goals);
		addClientWeb.enterDietPreferences("VEG");
		String result = addClientWeb.payment("monthly");
		Assert.assertEquals(result, "Client Registered Successfully", "Client registration failed");
	}

	@AfterTest
	public void closeBrowser() {
		DriverManager.getDriver().quit();
	}
}
