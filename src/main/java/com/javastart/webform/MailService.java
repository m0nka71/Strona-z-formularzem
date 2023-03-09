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

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Email email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessage replayMimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        MimeMessageHelper replayHelper = new MimeMessageHelper(replayMimeMessage, "utf-8");

        try {

            helper.setTo("testtest@javastart.pl");
            helper.setFrom(email.getEmail());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);

            replayHelper.setTo(email.getEmail());
            replayHelper.setFrom("testtest@javastart.pl");
            replayHelper.setSubject("Witaj");
            replayHelper.setText("Dzieki za wiadomosc! Niebawem odpowiemy :)", true);

            javaMailSender.send(mimeMessage);
            javaMailSender.send(replayMimeMessage);

        } catch (MessagingException e) {
            logger.warn("Błąd wysyłania wiadomosci", e);
        }

        logger.debug("Mail do {} został wysłany", email.getReceiver());
    }
}
