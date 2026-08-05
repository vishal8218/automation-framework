package trivia;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUser {
	
	private WebDriver driver;
	
	@FindBy(name="email")
	WebElement emailEle;
	
	@FindBy(name="password")
	WebElement passwordEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/form/div/div[4]/button")
	WebElement submitEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div[2]/div/a[5]/div")
	WebElement profileIconEle;
 
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div[1]/div[3]/div[1]/div/p")
	WebElement userDataEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div[2]/div/a[1]")
	WebElement marketEle;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div[1]/div/div/div[2]/div[2]/button")
	WebElement joinChallengeEle;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/button")
	WebElement joinChallenge;
	  private WebDriverWait wait ;

	 public LoginUser(WebDriver driver)
	   {
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
		   this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));


	   }
	 
	 public boolean isLoginUser(String email ,String pass)
	 {
		   this.wait.until(ExpectedConditions.visibilityOf(emailEle));
 
		 emailEle.click();
		 emailEle.sendKeys(email);
		 passwordEle.click();
		 passwordEle.sendKeys(pass);
		 submitEle.click();
		   this.wait.until(ExpectedConditions.visibilityOf(profileIconEle));

		 profileIconEle.click();
		 String userData=userDataEle.getText();
		 System.out.println(userData);
		 if(userData.equalsIgnoreCase(email))
		 {
			
			 return true;
			
		 }
		 else
		 {
			 return false;
		 }
		 
		 
	 }
	  
	 public boolean attemptChallenge()
	 {
		 marketEle.click();
		 joinChallengeEle.click();
		 return true;
	 }
	

}
