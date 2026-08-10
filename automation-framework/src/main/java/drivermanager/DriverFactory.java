package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.PageLoadStrategy;


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
	public static WebDriver createDriverPermisson(String browser, ChromeOptions options) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
				  boolean isCI = System.getenv("CI") != null;
                 if (isCI) {
                     options.addArguments("--headless=new");
                     options.addArguments("--no-sandbox");
                     options.addArguments("--disable-dev-shm-usage");
                     options.addArguments("--disable-gpu");
                     options.addArguments("--window-size=1920,1080");
					 options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                 }
                driver = new ChromeDriver(options);
                break;
            // add other browsers as needed (firefox, edge, etc.)
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }
}
