package com.solvd.utils;

import com.solvd.model.UserEmail;
import com.solvd.service.EmailService;
import com.solvd.service.EmailVerificationService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class EmailVerifier {

    public static void main(String[] args) {
        Properties envProps = new Properties();
        try {
            envProps.load(new FileInputStream(".env"));
        } catch (IOException e) {
            System.err.println("Error loading .env file: " + e.getMessage());
            return;
        }

        String email = envProps.getProperty("EMAIL");
        String password = envProps.getProperty("PASSWORD");
        String protocol = "IMAP";

        EmailService emailService = new EmailService();
        EmailVerificationService verificationService = new EmailVerificationService();

        System.out.println("Checking inbox...");
        List<UserEmail> emails = emailService.getEmails(email, password, protocol);

        System.out.println("Emails retrieved: " + emails.size());
        emails.forEach(System.out::println);

        String sender = "SendTestEmail.com";
        String subject = "Testing Email ID";

        boolean emailFound = verificationService.isEmailPresent(emails, sender, subject);
        System.out.println(emailFound ? "Email Found :)" : "Email Not Found.");
    }
}