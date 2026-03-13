package trivia;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChallengeCreation {
	
	private WebDriver driver;
	HashMap<String ,String>challengeCategory;
	Actions actions;
	Random random ;
    WebDriverWait wait;

	
	@FindBy(id="name")
	WebElement challengeNameEle;
	@FindBy(xpath="/html/body/div[3]/form/div[1]/div/div[1]/div[2]/select")
	WebElement cateSelectEle;
	@FindBy(id="description")
	WebElement desc;
	@FindBy(xpath="/html/body/div[3]/form/div[1]/div/div[3]/div/div/div/div")
	WebElement imageEle;
	@FindBy(id="entryFee")
	WebElement entryFeeEle;
	@FindBy(xpath="/html/body/div[3]/form/div[2]/div/div[1]/div[2]/select")
	WebElement durationEle;
	@FindBy(id="questionRecieve")
	WebElement requiredQuestionEle;
	@FindBy (id="minutes")
	WebElement minTimeEle;
	@FindBy(id="seconds")
	WebElement secTimeEle;
	@FindBy(xpath="/html/body/div[3]/form/div[3]/div/div[2]/button")
	WebElement addQuesEle;
	
	
	// Add Question paths   
	@FindBy(id="title")
	WebElement quesTitleEle;
	@FindBy(xpath="/html/body/div[5]/div[2]/div/div[2]/div/div/div[1]/input")
	WebElement optionEle1;
	@FindBy(xpath="/html/body/div[5]/div[2]/div/div[2]/div/div/div[2]/input")
	WebElement optionEle2;
	@FindBy(xpath="/html/body/div[5]/div[2]/div/div[2]/div/div/div[3]/input")
	WebElement optionEle3;
	@FindBy(xpath="/html/body/div[5]/div[2]/div/div[2]/div/div/div[4]/input")
	WebElement optionEle4;
	
	@FindBy(xpath="/html/body/div[5]/div[2]/div/div[3]/div/div/button")
	WebElement optionEle;
	
	@FindBy(xpath="/html/body/div[5]/div[2]/div/div[5]/button[2]")
	WebElement saveEle;
	@FindBy(xpath="//*[@id=\"radix-:r4q:\"]/div[1]/div/button")
	WebElement exitFromQues;
	
	@FindBy(name="platformFee")
	WebElement platFormFeeEle;
	
	//Buckets Elements
//	@FindBy(xpath="//*[@id=\"radix-:r2n:\"]/form/div[4]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div/div[4]/button")  // create function
	//*[@id="radix-:r2n:"]/form/div[4]/div[2]/div/div[1]/div[2]/div[2]/div[2]/div/div[4]/button
	
	@FindBy(xpath="//*[@id=\"radix-:r2n:\"]/form/div[4]/div[2]/div/div[1]/div[2]/div[1]/button")
	WebElement addBucketEle;
	
	@FindBy(xpath="/html/body/div[3]/form/div[6]/button[2]")
	WebElement createChalleneEle;
	

	public ChallengeCreation(WebDriver driver)
	{
		this.driver=driver;
		   PageFactory.initElements(driver, this);

		this.challengeCategory=new HashMap<String ,String>();
		challengeCategory.put("Tennis", "6");
		challengeCategory.put("Soccer", "5");
		challengeCategory.put("NHL", "4");
		challengeCategory.put("MLB", "3");
		challengeCategory.put("NBA", "2");
		challengeCategory.put("Golf", "1");
		actions=new Actions(this.driver);
	    this.random=new Random();
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void createChallenge(String challengeName, String category, String des,String image, String fee, String completionTime,String req,String timeMin ,String timeSec) throws AWTException
	{
		challengeNameEle.sendKeys(challengeName);
		Select select;

		if(category.equalsIgnoreCase("tennis"))
		{
			this.driver.findElement(By.xpath("/html/body/div[3]/form/div[1]/div/div[1]/div[2]/button")).click();
			 actions.sendKeys(Keys.ARROW_DOWN)
	         .sendKeys(Keys.ENTER)
	         .perform();
	
		}
		else
		{
		 select=new Select(cateSelectEle);
		select.selectByValue(challengeCategory.get(category));
		}
		desc.click();
		desc.sendKeys(des);
		imageEle.click();
		Robot robot = new Robot();
		robot.delay(2000);

		StringSelection file = new StringSelection(image);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);


		actions.moveToElement(entryFeeEle).perform();
		entryFeeEle.clear();
		entryFeeEle.sendKeys(fee);
	    select =new Select(durationEle);
	    select.selectByValue(completionTime);
	    requiredQuestionEle.clear();
	    requiredQuestionEle.sendKeys(req);
	   

	    wait.until(ExpectedConditions.elementToBeClickable(minTimeEle));

	    minTimeEle.click();
	    minTimeEle.sendKeys(Keys.CONTROL, "a");
	    minTimeEle.sendKeys(Keys.DELETE);
	    minTimeEle.sendKeys(timeMin);
	    secTimeEle.clear();
	    secTimeEle.sendKeys(timeSec);
	}
	
	public void addQuestion(int req,String cate)
	{
		addQuesEle.click();
		StringBuilder quest;
		ArrayList <String>options=new ArrayList<>();
		options.add(cate+"_A");
		options.add(cate+"_B");
		options.add(cate+"_C");
		options.add(cate+"_D");
		for(int i=1;i<=req;i++)
		{
			quest = new StringBuilder(cate);

			quest.append("_"+UUID.randomUUID().toString());
			this.driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div/div/div[1]/textarea")).clear();
			this.driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div/div/div[1]/textarea")).click();
			this.driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div/div/div[1]/textarea")).sendKeys(quest.toString());
			this.driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div[1]/input")).sendKeys(options.get(0));
			this.driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div[2]/input")).sendKeys(options.get(1));
			this.driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div[3]/input")).sendKeys(options.get(2));
			this.driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div/div/div[4]/input")).sendKeys(options.get(3));


			 actions.moveToElement(optionEle).perform();


			    wait.until(ExpectedConditions.elementToBeClickable(  optionEle));


			  optionEle.click();
             
			  actions.sendKeys(Keys.ARROW_DOWN)
			         .sendKeys(Keys.ENTER)
			         .perform();
			
			    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save Question']")));

            	   this.driver.findElement(By.xpath("//button[normalize-space()='Save Question']")).click();
               
               
            	   
               


		}
		
//		 actions.moveToElement(this.driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/button"))).perform();
//		 this.driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/button")).click();
//		actions.moveToElement(this.driver.findElement(By.xpath("/html/body/div[3]/form/div[6]/button[2]"))).perform();
//		this.driver.findElement(By.xpath("/html/body/div[3]/form/div[6]/button[2]")).click();
		
	
	}
	
	
}
