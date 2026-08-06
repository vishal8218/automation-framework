package usermanagement;

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

import utils.SliderUtils;

public class AddLeadWeb {
	private WebDriver driver;
	 private Actions actions ;
	  private WebDriverWait wait ;
	  private String number ,email;
	@FindBy(xpath="/html/body/div/div[1]/section[1]/div/div[1]/div/div/button")
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
	@FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div[2]/button[2]")
	
	WebElement nextEle5;
	
	
	public AddLeadWeb(WebDriver driver)
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
	public String enterDietPreferences(String diet, String health[]) throws InterruptedException {
	    // ---------- DIET SELECTION ----------
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

	    // ---------- HEALTH CONDITIONS SELECTION ----------
	    if (health != null) {
	        for (String condition : health) {
	            try {
	                By optionLocator = By.xpath(
	                    "//button[.//span[normalize-space(text())='" + condition + "']]"
	                );

	                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

	                // Check current state before clicking, to avoid accidentally deselecting
	                WebElement checkbox = option.findElement(By.cssSelector("button[role='checkbox']"));
	                boolean alreadyChecked = "true".equals(checkbox.getAttribute("aria-checked"));

	                if (!alreadyChecked) {
	                    option.click();
	                } else {
	                    System.out.println("Already selected, skipping: " + condition);
	                }
	            } catch (Exception e) {
	                System.out.println("Could not select health condition: " + condition + " — " + e.getMessage());
	            }
	        }
	    }

	    // ---------- NEXT NAVIGATION ----------
	    wait.until(ExpectedConditions.elementToBeClickable(nextEle4));
	    nextEle4.click();

	    wait.until(ExpectedConditions.elementToBeClickable(nextEle5));
	    nextEle5.click();
	    Thread.sleep(2000);
	    String url=this.driver.getCurrentUrl();
	    System.out.println("Current url " + url);
	    return url;
	}
}
