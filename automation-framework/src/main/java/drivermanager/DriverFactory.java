package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

   
	public static WebDriver createDriver(String browser) {

        WebDriver driver;

        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else {
            throw new RuntimeException("Invalid Browser");
        }

        driver.manage().window().maximize();
        return driver;
    }
}