package com.solvd.service;

import java.util.Properties;

public class EmailConfigService {

    public static Properties getEmailProperties(String protocol) {
        Properties properties = new Properties();
        if ("IMAP".equalsIgnoreCase(protocol)) {
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imaps.host", "imap.gmail.com");
            properties.put("mail.imaps.port", "993");
        } else if ("POP3".equalsIgnoreCase(protocol)) {
            properties.put("mail.store.protocol", "pop3s");
            properties.put("mail.pop3s.host", "pop.gmail.com");
            properties.put("mail.pop3s.port", "995");
        }
        return properties;
    }
}