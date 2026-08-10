package usermangement;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import usermanagement.AddClient;


public class AddClientTest {

	private WebDriver driver;

	@BeforeTest
	public void openBrowser()
	{
		this.driver=new ChromeDriver();
		this.driver.get("https://beta.btrainr.com/login");
		this.driver.manage().window().maximize();
		this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[1]/div[1]/div/input")).click();
		this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[1]/div[1]/div/input")).sendKeys("testing1137@yopmail.com");
		this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[1]/div[2]/div/input")).click();
		this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[1]/div[2]/div/input")).sendKeys("Qwerty@123");
		this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[1]/div[4]/button")).click();
	}
	
	@Test
	public void addClient() throws InterruptedException
	{
		AddClient addClient=new AddClient(this.driver);
		String email="testing10110192221@yopmail.com";
    	addClient.enterBasicDetails("Test",email,"8218394110","25","male","NA");
    	addClient.enterBodyDetails("cm","164","","","","70","VEG");
    	Map<String, String> keyGoals = new HashMap<>();
    	keyGoals.put("1", "Lose 5kg in 3 months");
    	keyGoals.put("2", "Become Senior QA Engineer");
    	keyGoals.put("3", "Master Selenium Automation");
    	keyGoals.put("4", "Save ₹1,00,000 this year");
    	String currentEmail=addClient.selectGoalsAndSubmit(keyGoals, "698ed7306e77767f24e94f2b");
    	System.out.println("CURRENT EMAIL "+currentEmail);
    	Assert.assertEquals(currentEmail, email);
	}
	
	@AfterTest
	public void browserClose()
	{
		this.driver.quit();
	}
}
