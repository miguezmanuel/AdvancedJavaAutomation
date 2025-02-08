import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DemoTests extends BaseTest{

    @Test
    @TestLabel(name = "feature", value = "retry_logic")
    public void testFailureOne() {
        System.out.println("Executing testFailureOne...");
        Assert.fail("Intentional failure in testFailureOne");
    }

    @Test
    public void testFailureTwo() {
        System.out.println("Executing testFailureTwo...");
        Assert.assertEquals(1, 2, "Intentional failure in testFailureTwo");
    }

    @Test
    public void testSuccess() {
        System.out.println("Executing testSuccess...");
        Assert.assertTrue(true);
    }

}
