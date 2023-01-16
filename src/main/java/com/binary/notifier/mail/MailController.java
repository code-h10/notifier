package com.binary.notifier.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("/simple")
    public void sendMail(MailDto mail) {
        mailService.sendEmail(mail);
    }

    @PostMapping("/attachment")
    public void sendMailWithAttachment(MailDto mail) {
        mailService.sendEmailWithAttachment(mail);
    }
}
