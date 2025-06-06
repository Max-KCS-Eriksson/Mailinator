package com.maxeriksson.mailinator.mail;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailService {

    private final String SENDER;
    private final Session SESSION;

    public MailService(
            @Value("${email.sender}") String email, @Value("${email.password}") String password) {
        this.SENDER = email;
        this.SESSION =
                Session.getInstance(
                        setSmtpSettings(),
                        new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(email, password);
                            }
                        });
    }

    private Properties setSmtpSettings() {
        Properties settings = new Properties();
        settings.put("mail.smtp.auth", "true");
        settings.put("mail.smtp.host", "smtp.office365.com");
        settings.put("mail.smtp.port", "587");
        settings.put("mail.smtp.starttls.enable", "true");
        return settings;
    }
}
