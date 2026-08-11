package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.PageLoadStrategy;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
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

                // Stability flags that apply whether or not we're headless.
                options.addArguments("--force-device-scale-factor=1");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);

                if (isCI) {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-gpu");
                }

                // Set window size explicitly in BOTH modes so local and CI
                // render at the same viewport — this is what was causing
                // "clickable at point (395, 3)": CI's viewport didn't match
                // what your locators/scroll assumptions were tuned for locally.
options.addArguments("--window-size=3840,2160");
                driver = new ChromeDriver(options);

                // maximize() is a no-op / can throw in headless mode, so only
                // call it when we're actually rendering a window (local runs).
              
                break;

            // add other browsers as needed (firefox, edge, etc.)
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }
}
