package com.maxeriksson.mailinator.mail;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@Service
public class MailService {

    private final String SENDER;
    private final Session SESSION;
    private final Optional<File> ATTACHMENT;

    public MailService(
            @Value("${email.sender}") String email,
            @Value("${email.password}") String password,
            @Value("${email.attachment}") String pathToAttachment) {
        this.SENDER = email;
        this.SESSION =
                Session.getInstance(
                        setSmtpSettings(),
                        new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(email, password);
                            }
                        });
        this.ATTACHMENT =
                (!pathToAttachment.isBlank())
                        ? Optional.of(new File(pathToAttachment))
                        : Optional.empty();
    }

    private Properties setSmtpSettings() {
        Properties settings = new Properties();
        settings.put("mail.smtp.auth", "true");
        settings.put("mail.smtp.host", "smtp.office365.com");
        settings.put("mail.smtp.port", "587");
        settings.put("mail.smtp.starttls.enable", "true");
        return settings;
    }

    private Multipart createMultipartContent(String text) {
        Multipart multipart = new MimeMultipart();

        try {
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(text);
            multipart.addBodyPart(textPart);

            if (ATTACHMENT.isPresent()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(ATTACHMENT.get());
                multipart.addBodyPart(attachmentPart);
            }
        } catch (IOException e) {
            String pathToAttachment = ATTACHMENT.get().getPath();
            throw new RuntimeException("Failed attaching file to email: " + pathToAttachment, e);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to create content of email", e);
        }
        return multipart;
    }
}
