package coach_management;

import java.time.Duration;

import org.openqa.selenium.By;
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
	   private Actions actions ;
	  private WebDriverWait wait ;
	  
	  @FindBy(name="email")
	 private  WebElement emailEle;
	  @FindBy(name="password")
		 private  WebElement passEle;
	  @FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[1]/div[4]/button")
	  private WebElement submitBtn;
	  @FindBy (xpath="//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[1]/button/img")
	  private WebElement profileIcon;
	  
	  @FindBy(xpath="//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[2]/div[1]/p[2]")
	  private WebElement emailEles;
	  
	  @FindBy(xpath="//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[2]/a/span")
	  private WebElement  profileEle;
	   
	   public LoginCoach(WebDriver driver)
	   {
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
			  actions = new Actions(driver);
			  this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));


	   }
	   
	   public String loginCoach(String email ,String pass) throws InterruptedException
	   {
		   emailEle.sendKeys(email);
		   passEle.sendKeys(pass);
		   submitBtn.click();
		 Thread.sleep(3000);		   profileIcon.click();
		   String coachEmail =emailEles.getText();
		   System.out.println("Email = "+coachEmail);
		   if(coachEmail.equalsIgnoreCase(email))
		   {
			   return "Coach logged in successfully";
		   }
		   else
		   {
			   return "Invalid credetials";
		   }
	   }
	   public void changeEmail() throws InterruptedException
	   {
		   Thread.sleep(2000);
		   profileEle.click();
		   profileIcon.click();
		   this.driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/main/div/div/div/div[2]/div/div[1]/div[1]/div/div[2]/button")).click();
	

		   JavascriptExecutor js = (JavascriptExecutor) driver;

		   js.executeScript(
		       "arguments[0].removeAttribute('disabled');",
		       emailEle
		   );
          emailEle.clear();
		   emailEle.sendKeys("newemail@test.com");
//		   this.driver.findElement(By.xpath("//*[@id=\"radix-:r4:-content-profile\"]/div/div[1]/div[2]/form/button")).click();
	   }
}
