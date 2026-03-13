package coach_management;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
	
	String brandName="ABC";
	String []role=new String[]{"Gym Owner","Fitness Coach","Fitness Influencer","Nutritionist/Dietitian","Other"}      ;                   //  gym-owner ,influencer ,nutritionist ,other
	String countryCode="United States";     //India  , United States  , United Arab Emirates ,Australia
	String client="50+";
	String  insta="https://www.instagram.com", youTube="https://www.youtube.com/";		

	String description ="Descriptions"; 
	String freeTrial="no";
	String tenure="Monthly";
	String promocode="TESTPROMOCODE123";
	
	
	String email = "coach" + System.currentTimeMillis() + "@yopmail.com";

	
	@DataProvider(name="signUp",parallel = true)
	public Object[][] signupData() {

	    return new Object[][] {


	        {"1335", email,"chrome" },                                       

	        
	    };
	}
	
	
	@Test(dataProvider="signUp")
	public void signUp(String lName, String emails,String browser)
	{

		
		DriverManager.setDriver(		DriverFactory.createDriver(browser));
		
		DriverManager.getDriver().get(signUpurl);
        DriverManager.getDriver().manage().window().maximize();
        String json = "{ \"email\": \"" + emails + "\", \"password\": \"" + password + "\" }";
		  FirebaseUtils.insertData("coachCredentials", json);
        CoachSignUp coachSignUp=new CoachSignUp(DriverManager.getDriver());
		coachSignUp.signUp(firstName, lName, emails, password, referralCode);
		coachSignUp.signUp2(brandName,role,countryCode,client,insta,youTube);
		coachSignUp.signUp3(serviceProvide, description);
	coachSignUp.planSelect(freeTrial,"",tenure,promocode);
		
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
	

	@Test
	public void bookDemo()
	{
		String demoUrl="https://beta.btrainr.com/demo";
		
		String fullName="Demo3";
		String email="demo39@yopmail.com";
		String countryCode="India";
		String phoneNum="8218495333";
		
		String role="coach";// ,"gym-owner","influencer","nutritionist","other"
		String clientRange="1";
		String date="12-02-2026";
		WebDriver driver=new ChromeDriver();
	DriverManager.setDriver(driver);
	DriverManager.getDriver().get(demoUrl);
    DriverManager.getDriver().manage().window().maximize();

		
		 BookDemo bookDemo=new BookDemo(DriverManager.getDriver());
		 if(bookDemo.bookDemo(fullName, email, countryCode, phoneNum, clientRange, clientRange, date))
		 {
			 System.out.println("Booked Demo Successfully !!!!");
		 }
		 else
		 {
			 System.out.println("Something went wrong while book demo");
		 }
			}	
	
	

}
