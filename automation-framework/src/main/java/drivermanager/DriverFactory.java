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

            options.addArguments("--force-device-scale-factor=1");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
   options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            if (isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
            }

            // Applies in both environments — headless CI gets the exact size,
            // local gets clamped to your monitor's real resolution (expected).
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);

            // No maximize() call — it would override --window-size and
            // defeat the point of pinning both environments to the same viewport.
            break;

        default:
            throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
    return driver;
}
}
