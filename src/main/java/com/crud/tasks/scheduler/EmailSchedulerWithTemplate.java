package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailSchedulerWithTemplate {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *") //fixedDelay = 10000
    //@Scheduled(cron="*/10 * * * * *")
    public void sendInformationEmail() {
        simpleEmailService.send(simpleEmailService.createMimeDailyMessage(new Mail(adminConfig.getAdminMail(), "", SUBJECT,
                "This is your daily reminder :)")));
    }
}
