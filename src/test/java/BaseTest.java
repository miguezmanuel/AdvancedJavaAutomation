import com.zebrunner.agent.core.registrar.Artifact;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

public class BaseTest {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void sampleBaseTest() {
        System.out.println("This is a sample test that will retry if it fails.");
    }

    @AfterMethod
    public void attachArtifact() {
        File reportFile = new File("test-report.txt");

        if (reportFile.exists()) {
            System.out.println("Attaching artifact: " + reportFile.getAbsolutePath());
            Artifact.attachToTest("Execution Report", reportFile);
            System.out.println("Artifact attached successfully");
        } else {
            System.out.println("The file does not exist");
        }
    }
}
