package workout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ExcerciseCreation {

	
	private WebDriver driver;
	   private Actions actions ;
	  private WebDriverWait wait ;
	  
	  @FindBy(xpath= "//span[@class='truncate' and @title='Exercise Database']")
	  WebElement excerciseDbEle;
	  @FindBy(xpath = "//button[normalize-space()='Create Exercise']")
	  private WebElement createExerciseBtn;
	  @FindBy(name = "name")
	    private WebElement exerciseNameInput;

	    @FindBy(xpath = "//label[text()='Exercise Category']/following-sibling::div//select")
	    private WebElement exerciseCategoryDropdown;

	    @FindBy(xpath = "//label[text()='Difficulty Level']/following-sibling::div//select")
	    private WebElement difficultyDropdown;

	    @FindBy(css = "div.ql-editor")
	    private WebElement descriptionEditor;

	    @FindBy(xpath = "//input[@placeholder='Enter tags separated by commas (e.g., legs, compound, barbell)']")
	    private WebElement tagsInput;

	    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Save']")
	    private WebElement saveBtn;
	    
	    @FindBy(xpath = "//button[@role='tab' and contains(., 'Exercise Metrics')]")
	    private WebElement excercisMerEle;
	    @FindBy(xpath = "//button[@role='tab' and contains(., 'Assign to Workouts')]")
	    private WebElement assignWorkoutsTabEle;

	   
	  
	  public  ExcerciseCreation(WebDriver driver)
	  {
		   
			   this.driver=driver;
			   PageFactory.initElements(driver, this);
				  actions = new Actions(driver);
				  this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));   
	  }
	  public void enterBasicDetail(String exerciseName, String category, String difficulty,
              String description, String tags) {
		  wait.until(ExpectedConditions.visibilityOf(excerciseDbEle));

		  excerciseDbEle.click();
		  wait.until(ExpectedConditions.visibilityOf(createExerciseBtn));

		  createExerciseBtn.click();

wait.until(ExpectedConditions.visibilityOf(exerciseNameInput));
exerciseNameInput.clear();
exerciseNameInput.sendKeys(exerciseName);

// Exercise Category
wait.until(ExpectedConditions.visibilityOf(exerciseCategoryDropdown));
new Select(exerciseCategoryDropdown).selectByVisibleText(category);

// Difficulty Level
wait.until(ExpectedConditions.visibilityOf(difficultyDropdown));
new Select(difficultyDropdown).selectByVisibleText(difficulty);

// Description (Quill rich text editor)
wait.until(ExpectedConditions.elementToBeClickable(descriptionEditor));
descriptionEditor.click();
descriptionEditor.sendKeys(description);

// Tags
wait.until(ExpectedConditions.visibilityOf(tagsInput));
tagsInput.clear();
tagsInput.sendKeys(tags);
}
	  @FindBy(id = "defaultSets")
	    private WebElement defaultSetsInput;

	    @FindBy(id = "defaultReps")
	    private WebElement defaultRepsInput;

	    @FindBy(id = "defaultRestTime")
	    private WebElement defaultRestTimeInput;

	    // Tempo = 4 separate number inputs (eccentric-bottom-concentric-top)
	    // No unique id/name, so anchored via the "Default Tempo" label's following container
	    @FindBy(xpath = "(//label[contains(., 'Default Tempo')]/following-sibling::div//input)[1]")
	    private WebElement tempoEccentricInput;

	    @FindBy(xpath = "(//label[contains(., 'Default Tempo')]/following-sibling::div//input)[2]")
	    private WebElement tempoBottomInput;

	    @FindBy(xpath = "(//label[contains(., 'Default Tempo')]/following-sibling::div//input)[3]")
	    private WebElement tempoConcentricInput;

	    @FindBy(xpath = "(//label[contains(., 'Default Tempo')]/following-sibling::div//input)[4]")
	    private WebElement tempoTopInput;

	    @FindBy(id = "defaultDuration")
	    private WebElement defaultDurationInput;

	    @FindBy(id = "defaultWeight")
	    private WebElement defaultWeightInput;

	   

	    // ==================== METHOD ====================

	    public void enterExerciseMetrics(String sets, String reps, String restTime,
	                                      String tempoEccentric, String tempoBottom,
	                                      String tempoConcentric, String tempoTop,
	                                      String duration, String weight) {
	    	wait.until(ExpectedConditions.elementToBeClickable(excercisMerEle)); // ADD THIS
	        actions.moveToElement(excercisMerEle).perform();
	        excercisMerEle.click();

	        // Default Sets
	        wait.until(ExpectedConditions.visibilityOf(defaultSetsInput));
	        defaultSetsInput.clear();
	        defaultSetsInput.sendKeys(sets);

	        // Default Reps
	        wait.until(ExpectedConditions.visibilityOf(defaultRepsInput));
	        defaultRepsInput.clear();
	        defaultRepsInput.sendKeys(reps);

	        // Default Rest Time
	        wait.until(ExpectedConditions.visibilityOf(defaultRestTimeInput));
	        defaultRestTimeInput.clear();
	        defaultRestTimeInput.sendKeys(restTime);

	        // Default Tempo (4 fields: eccentric-bottom-concentric-top)
	        wait.until(ExpectedConditions.visibilityOf(tempoEccentricInput));
	        tempoEccentricInput.clear();
	        tempoEccentricInput.sendKeys(tempoEccentric);

	        tempoBottomInput.clear();
	        tempoBottomInput.sendKeys(tempoBottom);

	        tempoConcentricInput.clear();
	        tempoConcentricInput.sendKeys(tempoConcentric);

	        tempoTopInput.clear();
	        tempoTopInput.sendKeys(tempoTop);

	        // Default Duration
	        wait.until(ExpectedConditions.visibilityOf(defaultDurationInput));
	        defaultDurationInput.clear();
	        defaultDurationInput.sendKeys(duration);

	        // Default Weight
	        wait.until(ExpectedConditions.visibilityOf(defaultWeightInput));
	        defaultWeightInput.clear();
	        defaultWeightInput.sendKeys(weight);
	    }
	    @FindBy(id = "workout-workout1")
	    private WebElement workoutFullBodyStrengthCheckbox;

	    @FindBy(id = "workout-workout2")
	    private WebElement workoutUpperBodyPushCheckbox;

	    @FindBy(id = "workout-workout3")
	    private WebElement workoutLowerBodyPowerCheckbox;

	    @FindBy(id = "workout-workout4")
	    private WebElement workoutRecoveryDayCheckbox;

	    @FindBy(id = "workout-workout5")
	    private WebElement workoutHiitCircuitCheckbox;

	    @FindBy(xpath = "//div[@role='tabpanel'][not(@hidden)]//button[@type='submit' and normalize-space()='Save']")
	    private WebElement workoutsSaveBtn;

	    // ==================== METHOD ====================

	    /**
	     * Selects workout templates by their visible label text.
	     * Example: assignToWorkouts("Full Body Strength", "Recovery Day");
	     */
	    public void assignToWorkouts(String... workoutNames) {
	    	assignWorkoutsTabEle.click();
	        for (String workoutName : workoutNames) {
	            By checkboxLocator = By.xpath(
	                "//label[normalize-space()='" + workoutName + "']/preceding-sibling::input[@type='checkbox'] " +
	                "| //label[normalize-space()='" + workoutName + "']/parent::div/preceding-sibling::input[@type='checkbox']"
	            );

	            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator));

	            if (!checkbox.isSelected()) {
	                checkbox.click();
	            } else {
	                System.out.println("Already selected, skipping: " + workoutName);
	            }
	        }
	    }


public void clickSave() {
wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
}
	  
}
