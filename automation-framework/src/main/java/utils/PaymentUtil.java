package utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentUtil {

    private static String cardNumber = "4242424242424242";
    private static String cvv = "123";
    private static String expiry = "10/55";
    private static String nameOnCard = "Vishal";

    public static boolean payment(WebDriver driver, WebDriverWait wait, String number, String email) {

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.cssSelector("iframe[name*='razorpay'], iframe[src*='razorpay']")
        ));

        WebElement contactField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='contactNumber']")));
        contactField.clear();
        contactField.sendKeys(number);

        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='email']")));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continue']")));
        continueBtn.click();

        clickReliably(driver, wait, By.cssSelector("[data-testid='Cards']"));

        WebElement cardNumberField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='card.number']")));
        cardNumberField.sendKeys(cardNumber);

        WebElement expiryField = driver.findElement(By.cssSelector("input[name='card.expiry']"));
        expiryField.sendKeys(expiry);

        WebElement cvvField = driver.findElement(By.cssSelector("input[name='card.cvv']"));
        cvvField.sendKeys(cvv);

        WebElement nameField = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='card.name']")));
        nameField.click();
        nameField.clear();
        nameField.sendKeys(nameOnCard);

        String actualValue = nameField.getAttribute("value");
        if (!nameOnCard.equals(actualValue)) {
            System.out.println("Name field didn't accept sendKeys, retrying via JS...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                nameField, nameOnCard
            );
        }

        WebElement continueBtn2 = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='bottom-cta-button']")));
        continueBtn2.click();

        // Save-card modal may or may not appear — handle both cases
        try {
            WebElement maybeLaterBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Maybe later']")));
            maybeLaterBtn.click();
        } catch (TimeoutException e) {
            System.out.println("Save-card modal did not appear — continuing.");
        }

        // Capture window handles BEFORE the new window opens
        String originalWindow = driver.getWindowHandle();

     // Instead of counting windows, directly wait for a window matching the bank auth page,
     // and switch to whichever handle isn't the original — regardless of when it opened.
     wait.until(d -> d.getWindowHandles().size() > 1);

     String bankWindow = null;
     for (String handle : driver.getWindowHandles()) {
         if (!handle.equals(originalWindow)) {
             bankWindow = handle;
             break;
         }
     }

     if (bankWindow == null) {
         throw new RuntimeException("No secondary window found for bank auth step.");
     }

     driver.switchTo().window(bankWindow);
     System.out.println("Switched to bank auth window: " + driver.getTitle());

     WebElement successBtn = wait.until(
             ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Success']")));
     successBtn.click();

     // Close bank window if still open, then return
     try {
         wait.until(d -> d.getWindowHandles().size() == 1);
         System.out.println("Bank window auto-closed after Success click.");
     } catch (TimeoutException e) {
         driver.close();
         System.out.println("Manually closed bank window.");
     }

     driver.switchTo().window(originalWindow);
     driver.switchTo().defaultContent();
     return true;
    }

  
    private static void clickReliably(WebDriver driver, WebDriverWait wait, By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
                el.click();
                return; // success
            } catch (StaleElementReferenceException e) {
                attempts++;
            } catch (Exception e) {
                attempts++;
                if (attempts == 3) {
                    WebElement el = driver.findElement(locator);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                    return;
                }
            }
        }
    }
}