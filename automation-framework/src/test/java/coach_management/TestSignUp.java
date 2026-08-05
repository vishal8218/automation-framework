package coach_management;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import listener.TestListener;
import usermanagement.AddClient;
import utils.ConfigReader;
import utils.VideoRecorder;


@Listeners(TestListener.class)   

public class TestSignUp {

	
	private String firstName="Coach";
	private	String password="Qwerty@123" , referralCode="";
	private String brandName="Titan Fitness Coaching";
	private String []role=new String[]{"Gym Owner","Fitness Coach","Fitness Influencer","Nutritionist/Dietitian","Other"}      ;                   //  gym-owner ,influencer ,nutritionist ,other
	private String countryCode="India";     //India  , United States  , United Arab Emirates ,Australia
	private	String client="50+";
	private	String  insta="https://www.instagram.com", youTube="https://www.youtube.com/";		
	private	String description ="Descriptions"; 
	private	String freeTrial="yes";
	private	String tenure="Monthly";
	private String promocode="TESTPROMOCODE123";
	private	long userName=System.currentTimeMillis();
	private	boolean isClientAdd=true;
	private	ConfigReader configReader;
	private String	email="coach" + userName + "@yopmail.com";
    VideoRecorder recorder;

	
	@DataProvider(name="signUp",parallel = true)
	public Object[][] signupData() {
	    return new Object[][] {
	        {"LAST NAME", email,"chrome" },   
	    };
	}
	
	@Test(dataProvider="signUp", enabled=true)
	public void signUp(String lName, String emails,String browser) throws Exception
	{
		 recorder =
			        VideoRecorder.startRecording(
			        "Sign-Up"
			        );
		DriverManager.setDriver(		DriverFactory.createDriver(browser));
		configReader =new ConfigReader();
		DriverManager.getDriver().get(configReader.getSignUpUrl());
        DriverManager.getDriver().manage().window().maximize();
        CoachSignUp coachSignUp=new CoachSignUp(DriverManager.getDriver());
		coachSignUp.signUp(firstName, lName, emails, password, referralCode);
		System.out.println("Email "+ emails);
		coachSignUp.signUp2("userName"+userName,role,countryCode,client,insta,youTube);
		coachSignUp.signUp3( description);

 //Assert.assertEquals(coachSignUp.planSelect(freeTrial,"",tenure,promocode,userName+"_UserName"), email);
		}

	@Test( enabled=false)
	public void bookDemo()
	{
		String demoUrl="https://beta.btrainr.com/demo";
		String fullName="Demo40";
		String email="demo43@yopmail.com";
		String countryCode="India";
		String phoneNum="8218495333";
		String role="gym-owner";
		String clientRange="6 - 10 clients";
		String date="17-03-2026";
		String timeSlot="07:00 PM - 07:30 PM";
		WebDriver driver=new ChromeDriver();
	DriverManager.setDriver(driver);
	DriverManager.getDriver().get(demoUrl);
    DriverManager.getDriver().manage().window().maximize();
//   BookDemo bookDemo=new BookDemo(DriverManager.getDriver());
//Assert.assertEquals(bookDemo.bookDemo(fullName, email, countryCode, phoneNum, role, clientRange, date,timeSlot), true, timeSlot);
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
	    	recorder.stop();

	    }
	}
	 @AfterTest
	    public void closeBrowser() throws IOException
	    {

	    	DriverManager.getDriver().quit();

	    }
}
