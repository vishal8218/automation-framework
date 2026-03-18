package coach_management;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import db.FirebaseUtils;
import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import listener.TestListener;

import static io.restassured.RestAssured.*;

@Listeners(TestListener.class)   

public class TestSignUp {

	private static final Object[] String = null;
	Map<String, String> serviceProvide = Map.ofEntries(
		    Map.entry("1","Workout Plans"),
		    Map.entry("2","Sessions"),
		    Map.entry("3","One on One Coaching")
		);
	String signUpurl="https://beta.btrainr.com/signup";
	String demoUrl="https://mypack.bcoder.co.in/demo";
	String loginUrl="https://mypack.bcoder.co.in/login";
	String firstName="Coach", lastName="1166";
	String password="Qwerty@123" , referralCode="";
	
	String brandName="CBC";
	String []role=new String[]{"Gym Owner","Fitness Coach","Fitness Influencer","Nutritionist/Dietitian","Other"}      ;                   //  gym-owner ,influencer ,nutritionist ,other
	String countryCode="United States";     //India  , United States  , United Arab Emirates ,Australia
	String client="50+";
	String  insta="https://www.instagram.com", youTube="https://www.youtube.com/";		

	String description ="Descriptions"; 
	String freeTrial="yes";
	String tenure="Monthly";
	String promocode="TESTPROMOCODE123";
	long userName=System.currentTimeMillis();

String	email="coach" + userName + "@yopmail.com";


	
	@DataProvider(name="signUp",parallel = true)
	public Object[][] signupData() {
		
	    return new Object[][] {


	        {"LAST NAME", email,"chrome" },   
	     

	        
	    };
	}
	


	
	@Test(dataProvider="signUp", enabled=false)
	public void signUp(String lName, String emails,String browser) throws InterruptedException
	{

		
		DriverManager.setDriver(		DriverFactory.createDriver(browser));
		
		DriverManager.getDriver().get(signUpurl);
        DriverManager.getDriver().manage().window().maximize();
        
        CoachSignUp coachSignUp=new CoachSignUp(DriverManager.getDriver());
		coachSignUp.signUp(firstName, lName, emails, password, referralCode);
		coachSignUp.signUp2("userName"+userName,role,countryCode,client,insta,youTube);
		coachSignUp.signUp3(serviceProvide, description);
		Assert.assertEquals(coachSignUp.planSelect(freeTrial,"",tenure,promocode,userName+"_UserName"), true);
	;
		
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
	

	@Test(enabled=true)
	public void bookDemo()
	{
		String demoUrl="https://beta.btrainr.com/demo";
		
		String fullName="Demo40";
		String email="demo40@yopmail.com";
		String countryCode="India";
		String phoneNum="8218495333";
		
		String role="gym-owner";// ,"gym-owner","influencer","nutritionist","other"
		String clientRange="6 - 10 clients";
		String date="17-03-2026";
		String timeSlot="07:00 PM - 07:30 PM";
		WebDriver driver=new ChromeDriver();
	DriverManager.setDriver(driver);
	DriverManager.getDriver().get(demoUrl);
    DriverManager.getDriver().manage().window().maximize();

		
		 BookDemo bookDemo=new BookDemo(DriverManager.getDriver());
		
			 Assert.assertEquals(bookDemo.bookDemo(fullName, email, countryCode, phoneNum, role, clientRange, date,timeSlot), true, timeSlot);
		 
		

	}

}
