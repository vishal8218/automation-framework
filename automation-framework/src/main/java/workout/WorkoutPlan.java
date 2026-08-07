package workout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorkoutPlan {
	
	private WebDriver driver;
	   private Actions actions ;
	  private WebDriverWait wait ;
	  String workoutName;
	  
	@FindBy(xpath = "//a[.//span[@title='Workout Library']] | //button[.//span[@title='Workout Library']]")
	private WebElement workoutLibraryLink;
	@FindBy(xpath = "//button[normalize-space()='Create Workout']")
	private WebElement createWorkoutBtn;
	 @FindBy(name = "workoutName")
	    private WebElement workoutNameInput;

	    @FindBy(css = "div.ql-editor")
	    private WebElement descriptionEditor;

	    @FindBy(xpath = "//label[.//span[contains(text(),'Exercise Category')]]/following-sibling::div//select")
	    private WebElement exerciseCategoryDropdown;

	    @FindBy(xpath = "//label[.//span[contains(text(),'Difficulty Level')]]/following-sibling::div//select")
	    private WebElement difficultyDropdown;

	    @FindBy(xpath = "//label[.//span[contains(text(),'Muscle Groups')]]/following-sibling::div//select")
	    private WebElement muscleGroupsDropdown;

	    @FindBy(xpath = "//label[.//span[contains(text(),'Workout Type')]]/following-sibling::div//select")
	    private WebElement workoutTypeDropdown;

	    @FindBy(xpath = "//input[@placeholder='Add tags...']")
	    private WebElement tagsInput;

	    @FindBy(xpath = "//input[@placeholder='Add tags...']/parent::div//button")
	    private WebElement addTagBtn;
	    @FindBy(xpath = "//button[normalize-space()='Add Exercise']")
	    private WebElement addExerciseBtn;
	    @FindBy(xpath = "//span[@class='text-xl font-bold' and normalize-space()='+']")
	    private WebElement plusIconSpa;
	    @FindBy(xpath = "//button[normalize-space()='Publish Workout']")
	    private WebElement publishWorkoutBtn;
	    @FindBy(xpath = "//h3[@class='text-lg font-semibold']")
    	private WebElement dynamicHeading;
	    @FindBy(xpath = "//h3[normalize-space()='Tags']")
	    private WebElement tagsHeading;
	    public  WorkoutPlan(WebDriver driver)
		  {
			   
				   this.driver=driver;
				   PageFactory.initElements(driver, this);
					  actions = new Actions(driver);
					  this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));   
		  }
	    
	    public void createWorkout(String workoutName, String description, String category,
                String difficulty, String muscleGroup, String workoutType,
                String tags,boolean addExercise) {
this.workoutName=workoutName;
// Navigate to Workout Library
wait.until(ExpectedConditions.elementToBeClickable(workoutLibraryLink));
workoutLibraryLink.click();

// Click "Create Workout"
wait.until(ExpectedConditions.elementToBeClickable(createWorkoutBtn));
createWorkoutBtn.click();

// Workout Name
wait.until(ExpectedConditions.visibilityOf(workoutNameInput));
workoutNameInput.clear();
workoutNameInput.sendKeys(workoutName);

// Description (Quill editor)
wait.until(ExpectedConditions.elementToBeClickable(descriptionEditor));
descriptionEditor.click();
descriptionEditor.sendKeys(description);

// Exercise Category
wait.until(ExpectedConditions.visibilityOf(exerciseCategoryDropdown));
new Select(exerciseCategoryDropdown).selectByVisibleText(category);

// Difficulty Level
wait.until(ExpectedConditions.visibilityOf(difficultyDropdown));
new Select(difficultyDropdown).selectByVisibleText(difficulty);

// Muscle Groups
wait.until(ExpectedConditions.visibilityOf(muscleGroupsDropdown));
new Select(muscleGroupsDropdown).selectByVisibleText(muscleGroup);

// Workout Type
wait.until(ExpectedConditions.visibilityOf(workoutTypeDropdown));
new Select(workoutTypeDropdown).selectByVisibleText(workoutType);

// Tags
wait.until(ExpectedConditions.visibilityOf(tagsInput));
tagsInput.clear();
tagsInput.sendKeys(tags);

// Click Add Tag (enabled once tagsInput has text)
wait.until(ExpectedConditions.elementToBeClickable(addTagBtn));
addTagBtn.click();
tagsHeading.click();
// Optionally add an exercise to the workout
if (addExercise) {
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Add Exercise']")));

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addExerciseBtn);
    js.executeScript("arguments[0].click();", addExerciseBtn);
}

// Bare "+" — flagged earlier as fragile; only use if this is confirmed correct
wait.until(ExpectedConditions.elementToBeClickable(plusIconSpa));
plusIconSpa.click();
}

	    public String publishWorkout() {

	        // Wait for button to be clickable before interacting
	        wait.until(ExpectedConditions.elementToBeClickable(publishWorkoutBtn));

	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", publishWorkoutBtn);

	        try {
	            publishWorkoutBtn.click();
	        } catch (ElementClickInterceptedException e) {
	            js.executeScript("arguments[0].click();", publishWorkoutBtn);
	        }

	        // Wait for heading to appear and populate with actual text
	        wait.until(ExpectedConditions.visibilityOf(dynamicHeading));
	        wait.until(driver -> !dynamicHeading.getText().trim().isEmpty());

	        String result = dynamicHeading.getText();
	        

	        return result;
	    }
}
