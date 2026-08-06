package usermangement;

import org.openqa.selenium.WebDriver;
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
	private String browser="chrome";
	private long userName=System.currentTimeMillis();
    private String	email="user" + userName + "@yopmail.com";
    private String fName="firstName";
private String lName="lastName";
private String phone="98701189954";
private int age=35;
private int height =172, weight =90;
private String goals[]=new String[]{"Loose Weight","Build Muscle"};
private String health []=new String [] {"Allergies","Chronic Disease","Mental Health"};
private String diet="veg";
	@BeforeTest
	public void openBrowser()
	{
		DriverManager.setDriver(		DriverFactory.createDriver(browser));
		this.configReader =new ConfigReader();
		this.driver=DriverManager.getDriver();
		this.driver.get( configReader.getCoachPortfolioLink());
         this.driver.manage().window().maximize();
	}
	@Test
	public void addLead() throws InterruptedException
	{
		AddLeadWeb addLeadWeb=new AddLeadWeb(this.driver);
		addLeadWeb.basicDetail(phone, fName, lName, email);
		addLeadWeb.enterPhysicalDetail("Female", age, "NA");

		addLeadWeb.enterPhysicalDetail(height, weight, goals);
		String url=addLeadWeb.enterDietPreferences(diet, health);
		Assert.assertEquals(url,configReader.getCoachPortfolioLink() );
	}
	@AfterTest 
	public void closeBrowser()
	{
		this.driver.quit();
	}
	
}
