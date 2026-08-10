package coach_management;

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
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginCoach {
	private WebDriver driver;
	private Actions actions;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	@FindBy(name = "email")
	private WebElement emailEle;
	@FindBy(name = "password")
	private WebElement passEle;
	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[1]/div[4]/button")
	private WebElement submitBtn;
	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[1]/button/img")
	private WebElement profileIcon;

	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[2]/div[1]/p[2]")
	private WebElement emailEles;

	@FindBy(xpath = "//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[2]/a/span")
	private WebElement profileEle;

	public LoginCoach(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.js = (JavascriptExecutor) driver;
	}

	/**
	 * Waits for an element to be clickable, scrolls it into view, then clicks.
	 * Falls back to a JS click if intercepted (common in CI where rendering
	 * timing or overlapping elements differ from local runs).
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

	public String loginCoach(String email, String pass) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(emailEle));
		emailEle.sendKeys(email);

		wait.until(ExpectedConditions.visibilityOf(passEle));
		passEle.sendKeys(pass);

		safeClick(submitBtn);

		// Wait for the post-login header/profile icon instead of a fixed sleep
		wait.until(ExpectedConditions.elementToBeClickable(profileIcon));
		safeClick(profileIcon);

		wait.until(ExpectedConditions.visibilityOf(emailEles));
		String coachEmail = emailEles.getText();
		System.out.println("Email = " + coachEmail);

		if (coachEmail.equalsIgnoreCase(email)) {
			return "Coach logged in successfully";
		} else {
			return "Invalid credetials";
		}
	}

	
}
