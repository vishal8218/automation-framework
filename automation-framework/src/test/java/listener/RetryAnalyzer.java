package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 1;
    private static final int maxRetry = 0;

    @Override
    public boolean retry(ITestResult result) {
    	System.out.println("Retrying test: " + result.getName());
        if (count < maxRetry) {
            count++;
            return true; 
        }
        return false; 
    }
}
