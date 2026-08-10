package usermangement;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import coach_management.LoginCoach;
import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import usermanagement.AddClient;
import utils.ConfigReader;

public class AddClientTest {
	private WebDriver driver;
	private LoginCoach loginCoach;
	private ConfigReader configReader;

	@BeforeTest
	public void openBrowser() throws InterruptedException {
		configReader = new ConfigReader();
		ChromeOptions options = new ChromeOptions();

		// Use the same CI-aware driver setup as the rest of the suite
		DriverManager.setDriver(DriverFactory.createDriverPermisson(configReader.getBrowser(), options));
		this.driver = DriverManager.getDriver();
		this.driver.manage().window().maximize();
		this.driver.get(configReader.getSignInUrl());

		// Reuse the existing, synchronized login flow instead of raw findElement calls
		loginCoach = new LoginCoach(this.driver);
		String loginResult = loginCoach.loginCoach("testing1137@yopmail.com", "Qwerty@123");
		Assert.assertEquals(loginResult, "Coach logged in successfully");
	}

	@Test
	public void addClient() throws InterruptedException {
		AddClient addClient = new AddClient(this.driver);
		String email = "testing1011019333221@yopmail.com";
		addClient.enterBasicDetails("Test", email, "8218394110", "25", "male", "NA");
		addClient.enterBodyDetails("cm", "164", "", "", "", "70", "VEG");
		Map<String, String> keyGoals = new HashMap<>();
		keyGoals.put("1", "Lose 5kg in 3 months");
		keyGoals.put("2", "Become Senior QA Engineer");
		keyGoals.put("3", "Master Selenium Automation");
		keyGoals.put("4", "Save ₹1,00,000 this year");
		String currentEmail = addClient.selectGoalsAndSubmit(keyGoals, "698ed7306e77767f24e94f2b");
		System.out.println("CURRENT EMAIL " + currentEmail);
		Assert.assertEquals(currentEmail, email);
	}

	@AfterTest
	public void browserClose() {
		DriverManager.getDriver().quit();
	}
}
