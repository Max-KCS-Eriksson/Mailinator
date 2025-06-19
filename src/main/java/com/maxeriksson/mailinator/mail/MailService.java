package com.maxeriksson.mailinator.mail;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@Service
@Slf4j
public class MailService {

    private final String SENDER;
    private final Session SESSION;
    private final Optional<File> ATTACHMENT;
    private static final int ATTACHMENT_INDEX = 1;

    public MailService(
            @Value("${email.sender}") String email,
            @Value("${email.password}") String password,
            @Value("${email.attachment}") String pathToAttachment) {
        this.SENDER = email;

        String smtpHost = "smtp.office365.com";
        int smtpPort = 587;
        this.SESSION =
                Session.getInstance(
                        setSmtpSettings(smtpHost, smtpPort),
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

    private Properties setSmtpSettings(String host, int port) {
        Properties settings = new Properties();
        settings.put("mail.smtp.auth", "true");
        settings.put("mail.smtp.host", host);
        settings.put("mail.smtp.port", Integer.toString(port));
        settings.put("mail.smtp.starttls.enable", "true");
        settings.put("mail.smtp.localhost", "localhost");
        return settings;
    }

    public void send(String recipient, String subject, String text) {
        Message message = new MimeMessage(SESSION);
        try {
            message.setFrom(new InternetAddress(SENDER));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            message.setSubject(subject);
            Multipart content = createMultipartContent(text);
            message.setContent(content);

            Transport.send(message);
            logSentMessage(message, content);
        } catch (AddressException e) {
            throw new RuntimeException("Failed to parse email address", e);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
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
                multipart.addBodyPart(attachmentPart, ATTACHMENT_INDEX);
            }
        } catch (IOException e) {
            String pathToAttachment = ATTACHMENT.get().getPath();
            throw new RuntimeException("Failed attaching file to email: " + pathToAttachment, e);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to create content of email", e);
        }
        return multipart;
    }

    private void logSentMessage(Message message, Multipart content) throws MessagingException {
        String logNote = "\nEmail sent to: " + message.getRecipients(Message.RecipientType.TO)[0];
        if (ATTACHMENT.isPresent()) {
            logNote += "\n   attachment: " + content.getBodyPart(ATTACHMENT_INDEX).getFileName();
        }
        log.info(logNote);
    }
}
