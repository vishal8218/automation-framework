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
	@FindBy (css ="#radix-\\:rt\\:-content-details > div:nth-child(2) > div.p-6.pt-0.space-y-4 > div.grid.grid-cols-2.gap-4 > div.col-span-2 > input")
	WebElement websiteEle;
	
}
