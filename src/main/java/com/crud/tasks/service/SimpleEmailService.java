package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Service
public class SimpleEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger((SimpleMailMessage.class));

    @Autowired
    private MailCreatorService mailCreatorService;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        send(createMimeMessage(mail));
    }

    public void send(final MimeMessagePreparator mailPreparator) {
        LOGGER.info("Starting email preparation...");
        try {
            javaMailSender.send(mailPreparator);
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    public MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mineMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mineMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }

    public MimeMessagePreparator createMimeDailyMessage(final Mail mail) {
        return mineMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mineMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildAllTaskEmail(mail.getMessage()), true);
        };
    }
/*
    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());

        if (mail.getToCc()!=null && !mail.getToCc().equals(""))
            mailMessage.setCc(mail.getToCc());

        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
*/

}
