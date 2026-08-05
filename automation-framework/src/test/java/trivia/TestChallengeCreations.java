package trivia;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import drivermanager.DriverManager;
import listener.TestListener;
import utils.ConfigReader;

public class TestChallengeCreations {
	
	
	private String url="https://admin.trivia.bcoder.co.in/login";
	private ChallengeCreation challengeCration;
	private ChromeDriver driver;
	private  ConfigReader configReader;

	@BeforeTest
	public void browserOpenAdmin()
	{
		this.driver=new ChromeDriver();
		this.configReader =new ConfigReader();
		this.driver.get(this.configReader.getTriviaAdminUrl());
		this.driver.manage().window().maximize();
	this.driver.findElement(By.id("email")).sendKeys(this.configReader.getAdminEmail());
	this.driver.findElement(By.id("password")).sendKeys(this.configReader.getAdminPass());
	this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[2]/form/button")).click();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	WebElement element = wait.until(
	    ExpectedConditions.elementToBeClickable(
	        By.xpath("//*[@id='root']/div[2]/div/div[1]/div[2]/div/div/div[2]/div/ul/li[3]/a")
	    )
	);
	element.click();
	this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/main/div/div[1]/div[2]/button")).click();
	this.driver.findElement(By.xpath("//*[@id=\"isFree\"]")).click();
	this.challengeCration=new ChallengeCreation(this.driver);

	}

	@Test
	public void challengeCre() throws AWTException
	{
		String challengeName="Elite Tennis Clash", category="Tennis", des="Experience professional-level competition with challenging matches, leaderboard rankings, and exciting rewards for top performers.";
		String image="C:\\Users\\vk368\\Downloads\\tennis.jpg", fee="200", completionTime="12";
		String req="10", timeMin="1" , timeSec="30";
		int reqAdd=20;
		this.challengeCration.createChallenge(challengeName, category, des, image, fee, completionTime, req, timeMin, timeSec);		
		this.challengeCration.addQuestion(reqAdd,category);
	} 
	
   	@AfterMethod
   	public void tearDown(ITestResult result) throws IOException {
   
   	    if (result.getStatus() == ITestResult.FAILURE) {
   	        String testName = result.getName();
   	        TakesScreenshot ts =
   	                (TakesScreenshot) DriverManager.getDriver();
   	        File src = ts.getScreenshotAs(OutputType.FILE);
   	        String path = "screenshots/" + testName + ".png";
   	        FileUtils.copyFile(src, new File(path));
   	    }
   	}
}
