package com.javastart.webform;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {

            helper.setTo(email.getReceiver());
            helper.setFrom(email.getEmail());
            helper.setText(email.getContent(), true);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            logger.warn("Błąd wysyłania wiadomosci", e);
        }

        logger.debug("Mail do {} został wysłany", email.getReceiver());
    }
}
