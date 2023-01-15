package com.binary.notifier.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(MailDto email) {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setTo(email.getAddress());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(email.getText(), true);
        } catch (MessagingException e) {
            log.error("---------- Fail To Send Email ----------", e);
            throw new MailParseException(e);
        }
        javaMailSender.send(mimeMessage);
    }
}
