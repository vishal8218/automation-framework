package excercise;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import coach_management.CoachSignUp;
import coach_management.LoginCoachTest;
import drivermanager.DriverFactory;
import drivermanager.DriverManager;
import utils.ConfigReader;
import workout.ExcerciseCreation;

public class ExcerciseTEST {
	private LoginCoachTest loginCoachTest;
	private ExcerciseCreation excerciseCreation;
	String assignWorkout[]=new String[]{"Full Body Strength","Upper Body Push", "Lower Body Power","Recovery Day","HIITCircuit"};

	
	private WebDriver driver;
	
	@BeforeTest 
	public void openBrowser() throws InterruptedException
	{
		
        loginCoachTest=new  LoginCoachTest ();
        loginCoachTest.openUrl ();
        loginCoachTest.loginCoach();
        excerciseCreation =new ExcerciseCreation(DriverManager.getDriver());
        
	}
	@Test
	public void createExcercise()
	{
		
		excerciseCreation.enterBasicDetail(
			    "Barbell Back Squat",
			    "Strength",
			    "Intermediate",
			    "A compound lower-body exercise targeting the quadriceps, glutes, and hamstrings. Keep your chest up, core braced, and knees tracking over your toes throughout the movement.",
			    "legs, compound, barbell, lower-body"
			);
		excerciseCreation.enterExerciseMetrics(
			    "4",       // sets — typical strength range: 3-5
			    "8",       // reps — typical hypertrophy/strength range: 6-12
			    "90",      // rest time (sec) — 60-120s is standard for compound lifts
			    "3", "1", "2", "0",  // tempo — 3s eccentric, 1s pause, 2s concentric, 0s top
			    "500",       // duration — 0 for rep-based exercises (only used for timed exercises like planks)
			    "60"       // weight — realistic starting barbell load in kg/lbs
			);
		 excerciseCreation.assignToWorkouts(assignWorkout);
		 
		 excerciseCreation.clickSave();
	}
	 @AfterTest
	    public void closeBrowser() throws IOException
	    {

	    	DriverManager.getDriver().quit();

	    }


}
