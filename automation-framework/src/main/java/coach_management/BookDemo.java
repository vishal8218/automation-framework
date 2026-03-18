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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookDemo {
	
	private WebDriver driver;
	   private Actions actions ;
		  private WebDriverWait wait ;


	
	@FindBy(id="fullName")
	WebElement fullNameEle;
	@FindBy(id="email")
	WebElement emailEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[3]/div/div/div[2]/div/div")
	WebElement countryEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[3]/div/div/input")
	WebElement phoneNumEle;
	
	
	
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[6]/button")
	WebElement client;
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[6]/select")
	WebElement clientSelect;
	
	@FindBy(name="demoDate")
	WebElement dateEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/div[8]/div/button[2]")
	WebElement timeEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/main/div/div/div[2]/form/button")
	WebElement demoBookEle;
	
	
	public BookDemo(WebDriver driver)
	{
		this.driver=driver;
		   PageFactory.initElements(this.driver, this);
		   actions=new Actions(this.driver);
			  this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));


	}
	
	/**
	 * @param fullName
	 * @param email
	 * @param country
	 * @param phoneNum
	 * @param clientRole
	 * @param clientRange
	 * @param date
	 * @return
	 */
	public boolean bookDemo(String fullName ,String email,String country,String phoneNum,String clientRole,String clientRange ,String date,String time )
	{
		fullNameEle.sendKeys(fullName);
		emailEle.sendKeys(email);
		countryEle.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[3]/div/div/div[2]/ul/li[1]/input")).click();
		this.driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[3]/div/div/div[2]/ul/li[1]/input")).sendKeys(country);

		 wait.until(ExpectedConditions.elementToBeClickable(
			        By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[3]/div/div/div[2]/ul/li[2]/span[1]")
			    )).click();
  
		 phoneNumEle.click();
		 phoneNumEle.sendKeys(phoneNum);
		  actions.moveToElement(demoBookEle).perform();
		  /*
		   * Here add client role automation script
		   */
		
		
		 client.click();
		Select select=new Select(clientSelect);
		 select.selectByValue(clientRange);
		 client.click();
		 dateEle.sendKeys(date);
		 wait.until(ExpectedConditions.elementToBeClickable(timeEle
			    )).click();

		
		 String timeSlot=timeEle.getText();
		 
		 if(timeSlot.equalsIgnoreCase(time))
		 {

			 WebElement bookBtn = this.wait.until(
			         ExpectedConditions.elementToBeClickable(
			                 By.xpath("//button[normalize-space()='Book My Demo']")
			         )
			 );

			 bookBtn.click();

			 return true;
		 }
		
		
	 return false;	
	}
	

}
