package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.EmailSender;


public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        try {
        	
            EmailSender.sendEmail(
                "Test Failed: " + result.getName(),
                "Failure detected in "+result.getInstanceName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}