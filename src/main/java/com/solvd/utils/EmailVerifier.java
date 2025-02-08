package com.solvd.utils;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.FlagTerm;

import java.util.Properties;

public class EmailVerifier {
    private static String HOST = "imap.gmail.com";
    private static String EMAIL;
    private static String PASSWORD;

    public static void checkInbox() {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", HOST);
        properties.put("mail.imaps.port", "993");

        try {
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("imaps");
            store.connect(HOST, EMAIL, PASSWORD);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));


            System.out.println("📧 Unread emails: " + messages.length);

            for (Message message : messages) {
                String subject = message.getSubject();

                if (subject != null && subject.contains("SendTestEmail we pge")) {
                    System.out.println("Processing Email from SendTestEmail.com:");
                    System.out.println("Received Date: " + message.getReceivedDate());
                    System.out.println("Subject: " + subject);
                    System.out.println("Email Body: " + getTextFromMessage(message));

                    message.setFlag(Flags.Flag.SEEN, true);


                }
            }


            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return (String) message.getContent();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            }
        }
        return result.toString();
    }
}
