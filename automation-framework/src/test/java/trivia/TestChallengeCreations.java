//package trivia;
//
//import java.awt.AWTException;
//import java.io.File;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import drivermanager.DriverManager;
//import listener.TestListener;
//@Listeners(TestListener.class)   
//
//public class TestChallengeCreations {
//	
//	
//	String url="https://admin.trivia.bcoder.co.in/login", userUrl="https://trivia.bcoder.co.in/verify-access-code";
//	private ChallengeCreation challengeCration;
//	private ChromeDriver driver;
//
//	@BeforeTest
//	public void browserOpenAdmin()
//	{
//		
//		this.driver=new ChromeDriver();
//		this.driver.get(url);
//		driver.manage().window().maximize();
//	this.driver.findElement(By.id("email")).sendKeys("triviaAdmin@yopmail.com");
//	this.driver.findElement(By.id("password")).sendKeys("Qwerty@123");
//	this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/div[2]/form/button")).click();
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//	WebElement element = wait.until(
//	    ExpectedConditions.elementToBeClickable(
//	        By.xpath("//*[@id='root']/div[2]/div/div[1]/div[2]/div/div/div[2]/div/ul/li[3]/a")
//	    )
//	);
//
//	element.click();
//
//	this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[2]/main/div/div[1]/div[2]/button")).click();
//	this.challengeCration=new ChallengeCreation(this.driver);
//
//	}
//	
//	
//	@Test
//	public void challengeCre() throws AWTException
//	{
//		String challengeName="Golf Challenge", category="Golf", des="des";
//		String image="C:\\Users\\vk368\\Downloads\\NHLS.jpg", fee="10", completionTime="12";
//		String req="10", timeMin="1" , timeSec="20";
//		int reqAdd=20;
//		this.challengeCration.createChallenge(challengeName, category, des, image, fee, completionTime, req, timeMin, timeSec);		
//		this.challengeCration.addQuestion(reqAdd,category);
//	}
//	   LoginUser login;
//       @BeforeTest
//       public void browserOpen()
//       {
//
//    	   ChromeOptions options = new ChromeOptions();
//
//    	   Map<String, Object> prefs = new HashMap<>();
//    	   prefs.put("profile.default_content_setting_values.notifications", 1); // 1 = Allow
//
//    	   options.setExperimentalOption("prefs", prefs);
//
//    	    driver = new ChromeDriver(options);
//
//    	   this.driver.get(userUrl);
//    	   
//    	   this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[1]/div")).click();
//    	   for(int i = 1; i <= 4; i++)
//    	   {
//    	       String ele = "//*[@id='root']/div/div/div/form/div[1]/div/input[" + i + "]";
//    	       this.driver.findElement(By.xpath(ele)).click();
//    	       this.driver.findElement(By.xpath(ele)).sendKeys("6");
//    	       
//    	   }
//           this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[2]/button")).click();
//    	   login=new LoginUser(driver);
//    	   
//    	   
//    	   
//       }
//       @Test
//       public void login()
//       {
//    	   Assert.assertEquals(login.isLoginUser("test44@yopmail.com", "Qwerty@123"), true, "Failed TEST");
//       }
//   	@AfterMethod
//   	public void tearDown(ITestResult result) throws IOException {
//   
//   	    if (result.getStatus() == ITestResult.FAILURE) {
//   
//   	        String testName = result.getName();
//   
//   	        TakesScreenshot ts =
//   	                (TakesScreenshot) DriverManager.getDriver();
//   
//   	        File src = ts.getScreenshotAs(OutputType.FILE);
//   
//   	        String path = "screenshots/" + testName + ".png";
//   
//   	        FileUtils.copyFile(src, new File(path));
//   
//   	    }
//   	}
//	
//
//}
