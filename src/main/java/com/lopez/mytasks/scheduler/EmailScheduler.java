package com.lopez.mytasks.scheduler;

import com.lopez.mytasks.config.AdminConfig;
import com.lopez.mytasks.domain.Mail;
import com.lopez.mytasks.repository.TaskRepository;
import com.lopez.mytasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String endText = size + " tasks";
        if (size == 1) {
            endText = size + " task";
        }
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(), "", SUBJECT, "Currently in database you got " + endText));
    }
}
