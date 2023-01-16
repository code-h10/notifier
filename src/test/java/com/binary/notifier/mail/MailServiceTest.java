package com.binary.notifier.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
class MailServiceTest {

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;


    @Test
    public void testSendMail() {

        final Context ctx = new Context();
        ctx.setVariable("name", "TestName");
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        ctx.setVariable("imageResourceName", "TestFileName");

        final String htmlContent = templateEngine.process("test", ctx);

        MailDto email = new MailDto();
        email.setAddress("test@naver.com");
        email.setSubject("Test");
        email.setText(htmlContent);

        mailService.sendEmail(email);

    }

}