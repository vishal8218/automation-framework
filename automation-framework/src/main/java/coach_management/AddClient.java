package coach_management;


import java.time.Duration;
import java.util.Map;

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

public class AddClient {

	private WebDriver driver;
	private Actions actions;
	private Select select;
	private WebDriverWait wait ;
	private String email;

	
	@FindBy(xpath="/html/body/div/div[1]/div/aside/div/nav/div[1]")
	WebElement clientEle;
	@FindBy(xpath="//button[normalize-space()='New Client']")
	WebElement newClientEle;
	@FindBy(id="fullName")
	WebElement fullNameEle;
	@FindBy(id="email")
    WebElement emailEle;
	@FindBy(xpath="/html/body/div[3]/form/div[1]/div[3]/div[2]/div/input")
	WebElement phoneEle;
	@FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[1]/div[4]/div[1]/div")
	WebElement ageEle;
	@FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[1]/div[4]/div[1]/div/select")
	WebElement ageSelectEle;
	@FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[1]/div[4]/div[2]/div")
	WebElement genderEle;
	@FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[1]/div[4]/div[2]/div/select")
	WebElement genderSelectEle;
	@FindBy(id="profession")
	WebElement professionEle;
	@FindBy(name="heightValue")
	WebElement heightEle;
	@FindBy(xpath="//*[@id=\"radix-:ri:\"]/form/div[1]/div[6]/div/div[1]/div[1]/div/div/div")
	WebElement unitChangeEleHeight;
	@FindBy(xpath="//*[@id=\"radix-:ri:\"]/form/div[1]/div[6]/div/div[1]/div[1]/div/div/div/select")
	WebElement unitChangeEleWeightSelect;
	@FindBy(name="heightFeet")
	WebElement heightFeetEle;
	@FindBy(name="heightInches")
	WebElement heightInchesEle;
	@FindBy(xpath="//*[@id=\"radix-:r25:\"]/form/div[1]/div[6]/div/div[2]/div[2]/div[1]/input")
	WebElement weightEle;
	@FindBy(xpath="//*[@id=\"radix-:ri:\"]/form/div[1]/div[6]/div/div[2]/div[1]/div/div/div/select")
	WebElement weightEleSelect;
	@FindBy(name="weightValue")
	WebElement weightValueEle;
	
	@FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[1]/div[7]/div/div")
	WebElement dietEle;
	@FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[1]/div[7]/div/div/select")
	WebElement dietSelectEle;
    @FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[1]/div[10]/div")
    WebElement assignTrainerEle;
    @FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[1]/div[10]/div/select")
    WebElement assignTrainerSelectEle;
    @FindBy(xpath="//*[@id=\"radix-:rn:\"]/form/div[2]/button")
    WebElement createClientEle;
    
    public AddClient(WebDriver driver)
    {
    	this.driver=driver;
    	 PageFactory.initElements(this.driver, this);
		  this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  this.actions=new Actions(this.driver);

    }
    public boolean enterBasicDetails(String fullName, String email,String number,String age,String gen,String profession)
    {
        this.wait.until(ExpectedConditions.elementToBeClickable(clientEle));
        this.email=email;
    	clientEle.click();
    	newClientEle.click();
    	fullNameEle.click();
    	fullNameEle.sendKeys(fullName);
    	emailEle.click();
    	emailEle.sendKeys(email);
    	phoneEle.click();
    	phoneEle.sendKeys(number);
    	ageEle.click();
    	this.select=new Select (ageSelectEle);
    	this.select.selectByValue(age);
    	genderEle.click();
    	this.select=new Select (genderSelectEle);
    	this.select.selectByValue(gen);
    	professionEle.click();
    	professionEle.sendKeys(profession);
		 this.actions.moveToElement(assignTrainerEle).perform();
    	return true;  	
    }
    public boolean enterBodyDetails(String unitHeight,String heightCm,String heightFt,String heightIn,String unitWeight,String weight,String diet)
    {
    	boolean isFormFill=false;
    	if(unitHeight.equalsIgnoreCase("ft") && heightFt!=null && heightIn!=null)
    	{
    		 unitChangeEleHeight.click();
    		this.select=new Select(unitChangeEleWeightSelect);
    		this.select.selectByValue(unitHeight);
    		heightFeetEle.click();
    		heightFeetEle.sendKeys(heightFt);
    		heightInchesEle.click();
    		heightInchesEle.sendKeys(heightFt);
    		if(unitWeight.equalsIgnoreCase("Pounds") && weight!=null)
    		{
    			 weightEle.click();
    			 this.select=new Select(weightEleSelect);
    			 this.select.selectByValue(unitWeight);
    			 weightEle.click();
    			 weightEle.sendKeys(weight);
    			 isFormFill=true;
    			 
    		}
    		else if (unitWeight.equalsIgnoreCase("KG") && weight!=null)
    		{
    			weightEle.click();
   			 weightEle.sendKeys(weight);
   			isFormFill=true;

    		}		
    	}
    	else if (unitHeight.equalsIgnoreCase("CM") && heightCm!=null)
    	{
    		heightEle.click();
    		heightEle.sendKeys(heightCm);
    		weightValueEle.click();
    		weightValueEle.sendKeys(weight);
  			isFormFill=true;
    		
    	}
    	if(isFormFill)
    	{
    		dietEle.click();
    		this.select=new Select(dietSelectEle);
    		this.select.selectByValue(diet);
    	    dietEle.click();
    		return isFormFill;
    	}
    	return isFormFill;
    }
    
    public boolean selectGoalsAndSubmit(Map<String, String> keyGoals,String trainerAssign ) throws InterruptedException
	   {
             
			//  JavascriptExecutor js = (JavascriptExecutor) this.driver;
			//  js.executeScript("window.scrollTo(0, 0)");
//			  Thread.sleep(2000);
			  System.out.println("Key Goal "+ keyGoals.size());
			   for (int i = 1; i <= keyGoals.size(); i++) {
				    if (keyGoals.get(String.valueOf(i)) != null) {
				        WebElement btn = driver.findElement(
				            By.xpath("//*[@id=\"radix-:rn:\"]/form/div[1]/div[8]/div/button[" + i+"]")
				        );

				        this.wait.until(ExpectedConditions.elementToBeClickable(btn));
				        btn.click();
				    }
				}
			   
			   assignTrainerEle.click();
			   this.wait.until(ExpectedConditions.elementToBeClickable(assignTrainerSelectEle));
			   this.select=new Select (assignTrainerSelectEle);

			   if (this.select.getOptions().size() > 0) {
				   this.select.selectByValue(trainerAssign);
				}
			 
				 this.actions.moveToElement(createClientEle).perform();
				 createClientEle.click();
				   this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.xpath("/html/body/div/div[1]/div/main/div/div/div[3]/div[2]/div[1]/div[1]/div[2]/div/div[1]"))));

				 this.driver.findElement(By.xpath("/html/body/div/div[1]/div/main/div/div/div[3]/div[2]/div[1]/div[1]/div[2]/div/div[1]")).click();
				 String temEmail=this.driver.findElement(By.xpath("/html/body/div/div[1]/div/main/div/div/div[2]/div/div[2]/div[2]/div[1]/p[2]")).getText();
				 System.out.println("Get Email "+temEmail);
				 if(temEmail.equalsIgnoreCase(this.email))
					 return true;
				 else
					 return false;
				 
}
}
