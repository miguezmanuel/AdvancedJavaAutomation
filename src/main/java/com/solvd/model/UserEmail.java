package com.solvd.model;

public class UserEmail {
    private String sender;
    private String subject;

    public UserEmail(String sender, String subject) {
        this.sender = sender;
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Email from: " + sender + " - Subject: " + subject;
    }
}