
import com.solvd.model.UserEmail;
import com.solvd.service.EmailVerificationService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class EmailVerificationTest {

    @Test
    public void testEmailVerification() {
        EmailVerificationService verificationService = new EmailVerificationService();

        List<UserEmail> emails = Arrays.asList(
                new UserEmail("SendTestEmail.com", "Testing Email ID 12345"),
                new UserEmail("noreply@google.com", "Security Alert")
        );

        boolean emailExists = verificationService.isEmailPresent(emails, "SendTestEmail.com", "Testing Email ID");
        Assert.assertTrue("The test email should exist in the inbox.", emailExists);
    }
}