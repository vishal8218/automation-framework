package drivermanager;


import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // set driver for current thread
    public static void setDriver(WebDriver driver) {
        tlDriver.set(driver);
    }

    // get driver of current thread
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // remove driver after execution
    public static void unload() {
        tlDriver.remove();
    }
}
