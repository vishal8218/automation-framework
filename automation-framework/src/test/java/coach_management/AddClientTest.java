package coach_management;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AddClientTest {

	private WebDriver driver;
	
	
	@BeforeTest
	public void openBrowser()
	{
		driver=new ChromeDriver();
		this.driver.get("https://beta.btrainr.com/login");
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
    	addClient.enterBasicDetails("Test","test1.1.1.2@yopmail.com","8218394110","25","male","NA");
    	addClient.enterBodyDetails("cm","164","","","","70","VEG");
    	Map<String, String> keyGoals = new HashMap<>();

    	keyGoals.put("1", "Lose 5kg in 3 months");
    	keyGoals.put("2", "Become Senior QA Engineer");
    	keyGoals.put("3", "Master Selenium Automation");
    	keyGoals.put("4", "Save ₹1,00,000 this year");
    	Assert.assertEquals(addClient.selectGoalsAndSubmit(keyGoals, "698ed7306e77767f24e94f2b"), true);
    	//addClient.selectGoalsAndSubmit(keyGoals, "698ed7306e77767f24e94f2b");
	}
}
