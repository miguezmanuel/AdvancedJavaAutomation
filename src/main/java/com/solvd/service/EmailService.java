package com.solvd.service;

import com.solvd.model.UserEmail;
import jakarta.mail.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailService {

    public List<UserEmail> getEmails(String email, String password, String protocol) {
        List<UserEmail> emails = new ArrayList<>();
        Properties properties = EmailConfigService.getEmailProperties(protocol);

        try {
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore(properties.getProperty("mail.store.protocol"));
            store.connect(email, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                emails.add(new UserEmail(message.getFrom()[0].toString(), message.getSubject()));
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emails;
    }
}