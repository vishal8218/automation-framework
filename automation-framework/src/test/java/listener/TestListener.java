package listener;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import drivermanager.DriverManager;
import utils.EmailSender;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String screenshotPath = null;

        // Capture the screenshot here, since onTestFailure fires before each
        // test class's own @AfterMethod tearDown runs — waiting on that
        // meant the file never existed yet when EmailSender tried to attach it.
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                File screenshotsDir = new File("screenshots");
                if (!screenshotsDir.exists()) {
                    screenshotsDir.mkdirs();
                }
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                screenshotPath = screenshotsDir.getAbsolutePath() + File.separator + testName + ".png";
                FileUtils.copyFile(src, new File(screenshotPath));
            } else {
                System.out.println("Skipping screenshot — driver is null for test: " + testName);
            }
        } catch (Exception e) {
            System.out.println("Could not capture screenshot for " + testName);
            e.printStackTrace();
        }

        try {
            EmailSender.sendEmail(
                "Test Failed: " + testName,
                "Failure detected in " + result.getInstanceName(),
                screenshotPath
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
