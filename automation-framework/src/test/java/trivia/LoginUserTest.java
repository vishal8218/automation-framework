package trivia;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import drivermanager.DriverManager;
import listener.TestListener;
@Listeners(TestListener.class)   

public class LoginUserTest {
	
	private WebDriver driver;
  String	userUrl="https://trivia.bcoder.co.in/verify-access-code";
  String email="user69@yopmail.com", pass="Qwerty@123";
	
	  LoginUser login;
      @BeforeTest
      public void browserOpen()
      {

   	   ChromeOptions options = new ChromeOptions();

   	   Map<String, Object> prefs = new HashMap<>();
   	   prefs.put("profile.default_content_setting_values.notifications", 1); // 1 = Allow

   	   options.setExperimentalOption("prefs", prefs);

   	    this.driver = new ChromeDriver(options);

   	   this.driver.get(userUrl);
   	   
   	   this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[1]/div")).click();
   	   for(int i = 1; i <= 4; i++)
   	   {
   	       String ele = "//*[@id='root']/div/div/div/form/div[1]/div/input[" + i + "]";
   	       this.driver.findElement(By.xpath(ele)).click();
   	       this.driver.findElement(By.xpath(ele)).sendKeys("6");
   	       
   	   }
          this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[2]/button")).click();
   	   
   	   
   	   
      }
      @Test
      public void login()
      {
      	   login=new LoginUser(driver);
      	 

            

      	   Assert.assertEquals(this. login.isLoginUser(email, pass), true);
      	  

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
