package com.solvd.service;

import com.solvd.model.UserEmail;

import java.util.List;

public class EmailVerificationService {

    public boolean isEmailPresent(List<UserEmail> emails, String sender, String subject) {
        return emails.stream()
                .anyMatch(email -> email.getSender().contains(sender) && email.getSubject().contains(subject));
    }
}