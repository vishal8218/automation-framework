package usermanagement;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PaymentUtil;
import utils.SliderUtils;

public class AddClientWeb {
	
	private WebDriver driver;
	 private Actions actions ;
	  private WebDriverWait wait ;
	  private String number ,email;
	 @FindBy(xpath = "//button[normalize-space()='Get Started']")
	WebElement getStatred;
	
	@FindBy(css="#radix-\\:r6\\:-content-phone > div > input")
	WebElement phoneEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[3]/button")
	WebElement getStartedbtn;
	
	
	@FindBy(id="firstName")
	WebElement firstNameEle;
    
	@FindBy(id="lastName")
	WebElement lastName;
	@FindBy(id="email")
	WebElement emailEle;
	
	// Goal  div.flex.flex-col.gap-4 > div > div:nth-child(1)  till4
	// diet .rounded-xl.p-6 > div > div:first-child
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[2]/button[2]")
	WebElement nextEle;
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[1]/div[1]/div/button[1]")
	WebElement genderEleM;
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[1]/div[1]/div/button[2]")
	WebElement genderEleFe;
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[1]/div[1]/div/button[3]")
	WebElement genderEleOth;

	@FindBy(id="profession")
	WebElement professionEle;
	
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[2]/button[2]")
	WebElement nextEle2;
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[2]/button[2]")
	WebElement nextEle3;
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[2]/button[2]")
    WebElement nextEle4;
	@FindBy(xpath="//button[normalize-space()='Continue']")
	WebElement nextEle5;
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div/div/div[1]/div[2]/div[2]/div/div[2]")
	WebElement planEle;
	public AddClientWeb(WebDriver driver)
	{
		this.driver=driver;
		 PageFactory.initElements(driver, this);
		  actions = new Actions(driver);
		  this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	public void basicDetail(String number,String fName,String lName , String email) throws InterruptedException
	{
		 this.wait.until(ExpectedConditions.visibilityOf(getStatred));
         this.number=number;
         this.email=email;
		
		getStatred.click();
		phoneEle.click();
		phoneEle.sendKeys(number);
	
		getStartedbtn.click();
		 this.wait.until(ExpectedConditions.visibilityOf(firstNameEle ));

		firstNameEle.sendKeys(fName);
		lastName.sendKeys(lName);
		emailEle.sendKeys(email);
		nextEle.click();	
		
	}
	
	public void enterPhysicalDetail(String gen,int age,String profession)
	{

       if(gen.equalsIgnoreCase("Male"))
			
		{
  		 this.wait.until(ExpectedConditions.visibilityOf(genderEleM )).click();;
		}
		else if(gen.equalsIgnoreCase("feMale"))
		{
	  		 this.wait.until(ExpectedConditions.visibilityOf(genderEleFe )).click();;


		}
		else if(gen.equalsIgnoreCase("other"))
		{
	  		 this.wait.until(ExpectedConditions.visibilityOf(genderEleOth )).click();;

			//genderEleOth.click();

		}
		 
			
		 WebElement slider = wait.until(
				    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='slider']"))
				);
				slider.click();

		 // the value you want to set
		int minValue = Integer.parseInt(slider.getAttribute("aria-valuemin")); 
		int currentValue = Integer.parseInt(slider.getAttribute("aria-valuenow"));

		int steps = age - currentValue;

		if (steps > 0) {
		    for (int i = 0; i < steps; i++) {
		        slider.sendKeys(Keys.ARROW_RIGHT);
		    }
		} else {
		    for (int i = 0; i < Math.abs(steps); i++) {
		        slider.sendKeys(Keys.ARROW_LEFT);
		    }
		    
		
			
		}
		professionEle.click();
		professionEle.sendKeys(profession);
		nextEle2.click();
	
	
	}
	public void enterPhysicalDetail(int height,int weight,String[]goals)
	{
		wait.until(
			    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div/main/div/div/div[2]/div[1]/div[1]/span/span[2]/span"))
			);
		
		WebElement silder=this.driver.findElement(By.xpath("/html/body/div/div[1]/div/main/div/div/div[2]/div[1]/div[1]/span/span[2]/span"));
		SliderUtils.setSliderValue(this.driver, this.wait, silder, height);
		silder=null;
		wait.until(
			    ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div/main/div/div/div[2]/div[1]/div[2]/span/span[2]/span"))
			);
		silder=this.driver.findElement(By.xpath("/html/body/div/div[1]/div/main/div/div/div[2]/div[1]/div[2]/span/span[2]/span"));
		SliderUtils.setSliderValue(this.driver, this.wait, silder, weight);
		List<String> goalOptions = Arrays.asList(goals);

		for (String goal : goalOptions) {
		    WebElement goalBtn = wait.until(
		        ExpectedConditions.elementToBeClickable(
		            By.xpath(String.format("//button[normalize-space()='%s']", goal))
		        )
		    );
		    goalBtn.click();

		   
		}
		  actions.moveToElement(nextEle3).perform();

		nextEle3.click();

	}
	public void enterDietPreferences(String diet) {
	    if (diet.equalsIgnoreCase("veg")) {
	        WebElement vegBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[1]/div[2]/div[2]/button[1]")));
	        vegBtn.click();
	    }
	    else if (diet.equalsIgnoreCase("Non-Veg")) {
	        WebElement nonVegBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[1]/div[2]/div[2]/button[2]")));
	        nonVegBtn.click();
	    }
	    else if (diet.equalsIgnoreCase("Vegan")) {
	        WebElement veganBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[1]/div[2]/div[2]/button[3]")));
	        veganBtn.click();
	    }
	    else if (diet.equalsIgnoreCase("Eggetarian")) {
	        WebElement eggBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[1]/div[2]/div[2]/button[4]")));
	        eggBtn.click();
	    }
	    else {
	        System.out.println("Invalid diet preference: " + diet);
	    }
	
nextEle4.click();
wait.until(ExpectedConditions.elementToBeClickable(nextEle5));


nextEle5.click();
wait.until(ExpectedConditions.elementToBeClickable(planEle));

planEle.click();

	}
	public String payment(String paymentType) throws IOException
	{
	  boolean result=	PaymentUtil.payment(driver, wait, this.number, this.email);
	  return result ? "Client Registered Successfully" : "Client Not Registered Successfully";
		
	}
	
}
