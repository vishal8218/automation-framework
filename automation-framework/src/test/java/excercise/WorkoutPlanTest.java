package excercise;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import coach_management.LoginCoachTest;
import drivermanager.DriverManager;
import workout.ExcerciseCreation;
import workout.WorkoutPlan;

public class WorkoutPlanTest {
	private LoginCoachTest loginCoachTest;
	private WorkoutPlan workoutPlan;
	
	@BeforeTest 
	public void openBrowser() throws InterruptedException
	{
		
        loginCoachTest=new  LoginCoachTest ();
        loginCoachTest.openUrl ();
        loginCoachTest.loginCoach();
        workoutPlan =new WorkoutPlan(DriverManager.getDriver());
        
	}
	@Test
	public void workoutPlanCreation()
	{
		workoutPlan.createWorkout(
			    "Full Body Strength Workout",
			    "A comprehensive full-body routine targeting all major muscle groups with compound movements.",
			    "Strength Training",
			    "Intermediate",
			    "Full Body",
			    "Strength",
			    "strength, full-body, compound",true
			);
	String actualResult=	workoutPlan.publishWorkout();
	Assert.assertEquals(actualResult, "Full Body Strength Workout");
		}
	
	@AfterTest
	public void closeBrowser()
	{
		DriverManager.getDriver().quit();
	}

}
