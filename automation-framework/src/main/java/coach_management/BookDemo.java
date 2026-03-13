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

	
	@FindBy(id="fullName")
	WebElement fullNameEle;
	@FindBy(id="email")
	WebElement emailEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[3]/div/div/div[2]/div/div")
	WebElement countryEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[3]/div/div/input")
	WebElement phoneNumEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[5]/button")
	WebElement role;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[5]/select")
	WebElement roleSelect;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[6]/button")
	WebElement client;
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[6]/select")
	WebElement clientSelect;
	
	@FindBy(id="demoDate")
	WebElement date;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/button")
	WebElement demoBookEle;
	
	
	public BookDemo(WebDriver driver)
	{
		this.driver=driver;
		   PageFactory.initElements(this.driver, this);
		   actions=new Actions(this.driver);

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
	public boolean bookDemo(String fullName ,String email,String country,String phoneNum,String clientRole,String clientRange ,String date )
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

		 
		 
		 role.click();
		 Select select=new Select (roleSelect);
		 select.selectByValue(clientRole);
		 client.click();
		 select=new Select(clientSelect);
		 select.selectByValue(clientRange);
		
		
	 return true;	
	}
	

}
