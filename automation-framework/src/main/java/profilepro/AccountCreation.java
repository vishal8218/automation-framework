package profilepro;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreation {
	
	
	@FindBy(xpath="//*[@id=\"radix-:r3n7:-content-details\"]/div[1]/div[2]/div/div[2]/input")
	WebElement accountNameEle;
	@FindBy(xpath="//*[@id=\"radix-:r3n7:-content-details\"]/div[1]/div[2]/div/div[3]/input")
	WebElement companyLegalNameEle;
	@FindBy(xpath="//*[@id=\"radix-:rod:-content-details\"]/div[1]/div[2]/div/div[4]/button")
	WebElement primaryIndustryEle;
	
}
