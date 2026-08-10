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
	private Actions actions;
	private WebDriverWait wait;
	private JavascriptExecutor js;
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

	public WorkoutPlan(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.js = (JavascriptExecutor) driver;
	}

	/**
	 * Waits for an element to be clickable, scrolls it into view, then clicks.
	 * Falls back to a JS click if the normal click is intercepted (common in CI
	 * where sticky headers/footers or slower rendering can overlap elements).
	 */
	private void safeClick(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		try {
			element.click();
		} catch (ElementClickInterceptedException e) {
			js.executeScript("arguments[0].click();", element);
		}
	}

	public void createWorkout(String workoutName, String description, String category,
			String difficulty, String muscleGroup, String workoutType,
			String tags, boolean addExercise) {
		this.workoutName = workoutName;

		// Navigate to Workout Library
		safeClick(workoutLibraryLink);

		// Click "Create Workout"
		safeClick(createWorkoutBtn);

		// Workout Name
		wait.until(ExpectedConditions.visibilityOf(workoutNameInput));
		workoutNameInput.clear();
		workoutNameInput.sendKeys(workoutName);

		// Description (Quill editor)
		safeClick(descriptionEditor);
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
		safeClick(addTagBtn);

		// Tags heading
		safeClick(tagsHeading);

		// Optionally add an exercise to the workout
		if (addExercise) {
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[normalize-space()='Add Exercise']")));
			safeClick(addExerciseBtn);
		}

		// "+" icon — flagged earlier as fragile; now uses the same safe click pattern
		safeClick(plusIconSpa);
	}

	public String publishWorkout() {
		safeClick(publishWorkoutBtn);

		// Wait for heading to appear and populate with actual text
		wait.until(ExpectedConditions.visibilityOf(dynamicHeading));
		wait.until(driver -> !dynamicHeading.getText().trim().isEmpty());

		return dynamicHeading.getText();
	}
}
