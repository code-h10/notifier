package com.binary.notifier.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
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

    public void sendEmail(MailDto mail) {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setTo(mail.getAddress());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText(), true);
        } catch (MessagingException e) {
            log.error("---------- Fail To Send Email ----------", e);
            throw new MailParseException(e);
        }
        javaMailSender.send(mimeMessage);
    }

    public void sendEmailWithAttachment(MailDto mail) {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
            messageHelper.setTo(mail.getAddress());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText(), true);

            final InputStreamSource attachmentSource = new ByteArrayResource(mail.getAttachmentBytes());
            messageHelper.addAttachment(mail.getAttachmentFileName(), attachmentSource);
        } catch (MessagingException e) {
            log.error("---------- Fail To Send Email With Attachment ----------", e);
            throw new MailParseException(e);
        }
        javaMailSender.send(mimeMessage);
    }
}
