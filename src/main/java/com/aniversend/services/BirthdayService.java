package com.aniversend.services;

import com.aniversend.models.People;
import com.aniversend.repositories.PeopleRepository;
import com.aniversend.util.MailTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BirthdayService {

    private final PeopleRepository peopleRepository;

    private final EmailService emailService;

    @Value("${number.email.template}")
    Integer numberEmailTemplate;

    public BirthdayService(PeopleRepository peopleRepository, EmailService emailService) {
        this.peopleRepository = peopleRepository;
        this.emailService = emailService;
    }

    //    @Scheduled(cron = "0 50 6 * * ?") // Executa todos os dias às 06:50 da manhã
    @Scheduled(fixedRate = 30000) // Executa a cada 30 segundos
    public void printConsoleMessageAndSendEmail() {
        Date today = new Date();
        long numberOfPeoples = peopleRepository.count();
        List<People> peoplesWithBirthday = peopleRepository.findByBirthday(today);

        Logger.getGlobal().info("Number of peoples found: " + numberOfPeoples);
        Logger.getGlobal().info("Number of peoples celebrating their birthday today: " + peoplesWithBirthday.size());

        for (People people : peoplesWithBirthday) {
            String subject = "Happy birthday " + people.getName() + "!";
            String message = MailTemplate.getMessage(people, numberEmailTemplate);
            String recipientEmail = people.getEmail();

//			emailService.sendEmail(recipientEmail, subject, message);
//			Logger.getGlobal().info("Email sent to: " + people.getEmail());
        }
    }
}
