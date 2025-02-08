import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int maxRetryCount = getRetryCount();
    private ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(() -> 0);

    @Override
    public boolean retry(ITestResult result) {
        int currentRetry = retryCount.get();
        if (currentRetry < maxRetryCount) {
            retryCount.set(currentRetry + 1);
            System.out.println("Retrying test: " + result.getName() + " | Attempt " + (currentRetry + 1));
            return true;
        }
        return false;
    }

    private static int getRetryCount() {
        String retryCountProperty = System.getProperty("retry.count", System.getenv("RETRY_COUNT"));
        return retryCountProperty != null ? Integer.parseInt(retryCountProperty) : 3;
    }
}
