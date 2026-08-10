package coach_management;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CoachSignUp {
	private WebDriver driver;
	   private Actions actions ;
	  private WebDriverWait wait ;
	  private String coachemail;
	  
	   
	   public CoachSignUp(WebDriver driver)
	   {
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
			  actions = new Actions(driver);
			  this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));


	   }
	   
	   @FindBy(xpath="//*[@id=\"firstName\"]")
	   WebElement fName;
	   
	   @FindBy(xpath="//*[@id=\"lastName\"]")
	   WebElement lName;
	   
	   @FindBy(name="email")
	   WebElement email;
	   
	   
	   
	   @FindBy(name="password")
	   WebElement pass;
	   
	  
//	   @FindBy(name="referralCode")
//	   WebElement referralCode;
	   
	   @FindBy(xpath="/html/body/div/div[1]/div[2]/div/main/div/div/div[2]/form/div[2]/button")
	   WebElement next;
	   // Second Page
	   @FindBy(id="businessName")
	   WebElement brandName;
	   
	   
	   
	   @FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[3]/button")
	   WebElement countryEle;
	   
	    @FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[3]/select")
	    WebElement currencySelectEle;
	   
	   
	   @FindBy(name="instagram")
	   
	   WebElement instaGram;
	   @FindBy(name="youtube")
	   WebElement youTube;
	   
	   @FindBy(xpath="/html/body/div/div[1]/div[2]/div/main/div/div/div[2]/form/div[2]/button")
	   WebElement next2;
	   
	
	   
	   @FindBy(id="uniqueServices")
	   WebElement descriptionEle;
	   
	   @FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[2]/button[2]")
	   WebElement next3;
	   
	   @FindBy(xpath="//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[2]/button")
	   WebElement freeTrialEle;
	   
	   @FindBy(id="uniqueUrl")
	   WebElement uniqueUrlEle;
	   
	   @FindBy(xpath="//*[@id=\"root\"]/div/div/main/div/div/div[2]/form/div[1]/div[1]/div/button[2]")
	   WebElement tenureEle;
	   
	   
	   @FindBy(xpath="/html/body/div/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[3]/div[1]/div[2]/div[2]") //2- pro ,1-professional
	   
	   WebElement plan;
	   
	   @FindBy(xpath="/html/body/div[3]/div[2]/div[2]/div/div[1]/div[4]/div[2]/div/div[2]/div")
	   WebElement ammountEle;
	   @FindBy (xpath="/html/body/div[3]/div[2]/div[1]/div[1]/div[3]/div/input")
	   WebElement promoCodeEle;
	   
	   @FindBy(xpath="/html/body/div[3]/div[2]/div[1]/div[1]/div[3]/div/button")
	   WebElement applyEle;
	   
	   @FindBy(xpath="//button[@type='button' and contains(.,'Proceed to Payment')]")
	   WebElement paymentButton;
	   @FindBy(xpath="/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/form/div[1]/div/div[2]/label/input")
	   WebElement phoneEle;
	   @FindBy(xpath="/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/form/div[1]/div/div[3]/label/input")
	   WebElement emailEle;
	   @FindBy (name="button")
	   WebElement continueWeb;
	   
	   @FindBy(xpath="/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/div/form/div[2]/div/label/div/div/div[1]/span[1]/span")
	   WebElement cardPayment;
	   
	   @FindBy(xpath="/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/div/div[4]/div/form/div[1]/div[1]/label/span[2]")
	   WebElement cardNumEle;
	   
	   @FindBy(xpath="//*[@id=\"main-stack-container\"]/div/div/div/div/div[4]/div/form/div[1]/div[2]/label[1]/span[2]")
	   WebElement monthEle;
	   
	   @FindBy(xpath="/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/div/div[4]/div/form/div[1]/div[2]/label[2]/span[2]")
	   WebElement cvvEle;
	   
	   @FindBy(xpath="/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/div/div[4]/div/form/div[1]/label/span[2]")
	   
	   WebElement cardHolderEle;
	   
	   @FindBy(xpath="/html/body/div/div[1]/div/div[3]/div[2]/div/div[2]/button")
	   WebElement continueElement;
	   
	   @FindBy(xpath="/html/body/div/div[3]/div/div/div/div/div/div/div/div/div[2]/button[1]")
	   WebElement mayBeLater;
	   
	   @FindBy(xpath="//*[@id=\"root\"]/div[1]/div/aside/div/nav/div[1]/ul/li[3]/a/span[2]")
	   WebElement clientTab;
	   
	   @FindBy(xpath="//*[@id=\"root\"]/div[1]/div/main/div/div/div[1]/div[2]/div[4]/button")
	   WebElement temp;

	   public void signUp(String firstName,String lastName ,String emails,String passWord , String reffralCode) throws Exception
	   {
		   this.coachemail=emails;
		  fName.click();
		  fName.sendKeys(firstName);
		  lName.click();
		  lName.sendKeys(lastName);
		  
		  actions.moveToElement(next).perform();
		  email.click();
		  email.sendKeys(emails);
		 
		 
		  pass.click();
		  pass.sendKeys(passWord);
//	  referralCode.click();
//		  referralCode.sendKeys(reffralCode);
		  next.click();
		 WebElement toastClose = this.driver.findElement(By.cssSelector("[class*='toast'] button, [role='alert'] button"));
    if (toastClose.isDisplayed()) {
        toastClose.click();
    }
));

		 


	   }
	   public void signUp2(String bName,String accessRole[],String currencyType,String clinet,String insta,String youtube)
	   {
	 

		   wait.until(ExpectedConditions.visibilityOf(brandName));

		   ((JavascriptExecutor) driver).executeScript(
		           "arguments[0].scrollIntoView({block:'center', inline:'center'});",
		           brandName
		   );

		   wait.until(ExpectedConditions.elementToBeClickable( brandName));

		   brandName.click();
		   brandName.sendKeys(bName);

		  
		   for(String tem:accessRole)
		   {
			   if(tem.equalsIgnoreCase("Fitness Coach"))
			   {
				   this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[2]/div/button[1]")).click();
			   }
			   else if(tem.equalsIgnoreCase("Gym Owner"))
			   {
				   this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[2]/div/button[2]")).click();

			   }
			   else if(tem.equalsIgnoreCase("Fitness Influencer"))
			   {
				   this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[2]/div/button[3]")).click();

			   }
			   else if(tem.equalsIgnoreCase("Nutritionist/Dietitian"))
			   {
				   this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[2]/div/button[4]")).click();

			   }
			   else if(tem.equalsIgnoreCase("Other"))
			   {
				   this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[2]/div/button[5]")).click();

			   }
		   }
			  actions.moveToElement(next2).perform();

	     
	       if(currencyType.equalsIgnoreCase("india"))
	       {
	    	   countryEle.click();
				 actions.sendKeys(Keys.ARROW_DOWN)
		         .sendKeys(Keys.ENTER)
		         .perform();
	       }
	       else
	       {
	       Select select=new Select(currencySelectEle);
	       select.selectByValue(currencyType);
			  actions.moveToElement(next2).perform();

	       countryEle.click();
	       
	       
	       }
	       instaGram.click();
	       instaGram.sendKeys(insta);
	       youTube.click();
	       youTube.sendKeys(youtube);
    next2.click();  
    }
	   public void signUp3(String description ) throws InterruptedException
	   {
                
			 wait.until(ExpectedConditions.elementToBeClickable(descriptionEle));
		   descriptionEle.click();
		   descriptionEle.sendKeys(description);

		   wait.until(ExpectedConditions.elementToBeClickable(next3));
		   next3.click();

	   }
	   public String planSelect(String typePlan, String planName ,String tenure,String promoCode,String bName) throws Exception
	   {
		   if(typePlan.equalsIgnoreCase("YES"))
		   {
			  this. wait.until(ExpectedConditions.visibilityOf(freeTrialEle));

			   freeTrialEle.click();
			   this.wait.until(ExpectedConditions.visibilityOf( uniqueUrlEle));
			   uniqueUrlEle.click();
			   uniqueUrlEle.sendKeys(bName);
			   this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.xpath("/html/body/div/div[1]/main/header/div[2]/button"))));
			   this.driver.findElement(By.xpath("/html/body/div/div[1]/main/header/div[2]/button")).click();
			   
			   

			   WebElement skipBtn = this.wait.until(
			       ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Skip']"))
			   );

			   skipBtn.click();

			   this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[1]/button/span"))));

			   WebElement profileIconEle=this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[1]/button/span"));
			   profileIconEle.click();
			   String coachEmail=this.driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/header/div/div[3]/div[2]/div[2]/div[1]/p[2]")).getText();
			   System.out.println("Inside Coach Sign up "+coachEmail);

			    return coachEmail;

			   
		   }
		   else
			   return null;
		   
//		   if(tenure.equalsIgnoreCase("Yearly"))
//		   {
//			   tenureEle.click();
//			   
//		   }
//		   WebElement element = wait.until(
//				    ExpectedConditions.visibilityOfElementLocated(
//				        By.xpath("/html/body/div/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[3]/div[1]")
//				    )
//				);
//
//				// Scroll to element
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
//			  this.driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div/main/div/div/div[2]/form/div[1]/div[3]/div[1]")).click();
//		   this.wait.until(ExpectedConditions.visibilityOf(ammountEle));
//		   StringBuilder ammount=new StringBuilder(ammountEle.getText().replaceAll("[^0-9]", ""));
//		   int temAm=Integer.parseInt(ammount.toString());
//		   if(!promoCode.equalsIgnoreCase(""))
//		   {
//			   promoCodeEle.click();
//			   promoCodeEle.sendKeys(promoCode);
//			   this.driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]/div[3]/div/button")).click();
//
//			   this. wait.until(ExpectedConditions.visibilityOf(paymentButton));
//			   this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[1]/div[3]/div/button"))));
//			   this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[1]/div[4]/div[2]/div/div[2]/div[1]"))));
//			   ammount=new StringBuilder(this.driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[1]/div[4]/div[2]/div/div[2]/div[1]")).getText().replaceAll("[^0-9]", ""));
//		   	   int ammountAfter=Integer.parseInt(ammount.toString());
//		   	   if(ammountAfter>0 && temAm!=ammountAfter)
//		   	   {
//		   		   this. wait.until(ExpectedConditions.visibilityOf(paymentButton));
//				   paymentButton.click();
//				   System.out.println("Before Promocode plan value = "+temAm);
//			   	   System.out.println("After = "+ammountAfter);
//
//
//		   	   }
//		   	   else
//		   	   {
//		   	  
//	   		   this. wait.until(ExpectedConditions.visibilityOf(paymentButton));
//
//			   paymentButton.click();
//		   	   }
//		   }
//		   else
//		   {
//			   this. wait.until(ExpectedConditions.visibilityOf(paymentButton));
//			   paymentButton.click();
//		  
//		   }
//		   
//
//		   	 
//		   
//		   
//		
//		   this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.xpath("/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/form/div[1]/div/div[2]/label/input"))));
//		   this.driver.findElement(By.xpath("/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/form/div[1]/div/div[2]/label/input")).click();		
//		   this.driver.findElement(By.xpath("/html/body/div/div[1]/div/div[3]/div[1]/div[2]/div/div/div/form/div[1]/div/div[2]/label/input")).sendKeys("9999999999");
//		   emailEle.click();
//		   emailEle.sendKeys("xyz@yopmail.com");
//		   continueWeb.click();
//		   cardPayment.click();
//		   cardNumEle.click();
//		   cardNumEle.sendKeys("411111111");
//		   monthEle.click();
//		   monthEle.sendKeys("1230");
//		   cvvEle.click();
//		   cvvEle.sendKeys("123");
//		   cardHolderEle.click();
//		   cardHolderEle.sendKeys("TEST");
//		   continueElement.click();
//		   mayBeLater.click();  
//		   return true;
	   }
	   
	 

}
